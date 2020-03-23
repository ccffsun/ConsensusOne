package pages.settlement;

import db.OrderDAO;
import db.ProductDAO;
import db.ProjectDAO;
import dnl.utils.text.table.TextTable;
import entity.Order;
import entity.Product;
import entity.Project;
import pages.ActionPage;
import pages.helper.Common;
import ui.Constant;

import java.sql.SQLException;
import java.util.*;

public class SettlementPage extends ActionPage {
    ProjectDAO projectDAO;
    OrderDAO orderDAO;
    ProductDAO productDAO;
    double totalRent;
    List<Object[]> tableRows;

    public SettlementPage() {
        super();
        setPageName("Settlement");
    }

    @Override
    protected boolean runPage() {
        projectDAO = new ProjectDAO();
        orderDAO = new OrderDAO();
        productDAO = new ProductDAO();

        try {
            System.out.println(Constant.ProjectId);
            int projectId = Integer.valueOf(scanner.nextLine());
            Project project = projectDAO.getProject(projectId);
            if (project == null) {
                System.out.println(Constant.ProjectNotExist);
                return true;
            }
            if (!project.getStatus().equals(Constant.CLOSED)) {
                System.out.println("Project is not closed yet!!!");
                return true;
            }
            List<Order> orders = orderDAO.getOrdersByProjectId(projectId);
            if (!doSettlement(orders, project.getEndDate())) {
                System.out.println(Constant.Failed);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return true;
    }

    private boolean doSettlement(List<Order> orders, Date endDate) throws SQLException {
        tableRows = new ArrayList<>();
        totalRent = 0;
        HashMap<Integer, Queue<Order>> orderQueueMap = new HashMap<>();
        HashMap<Integer, Double[]> priceMap = new HashMap<>();
        for (Order o : orders) {
            if (o.getType() == Constant.LEASE) {
                //Queue<Order> q = map.getOrDefault(o.getProductId(), new LinkedList<>());
                Queue<Order> q;
                if (orderQueueMap.containsKey(o.getProductId())) {
                    q = orderQueueMap.get(o.getProductId());
                } else {
                    q = new LinkedList<>();
                    orderQueueMap.put(o.getProductId(), q);
                }
                q.add(o);
            } else {
                if (!orderQueueMap.containsKey(o.getProductId())) {
                    System.out.println("Return product " + o.getProductId() + " not exist!!!");
                    return false;
                }
                Queue<Order> leaseQueue = orderQueueMap.get(o.getProductId());
                int returnAmount = o.getQuantity();
                while (returnAmount > 0) {
                    Order leaseOrder = leaseQueue.peek();
                    int amoutToCalculate = 0;
                    if (leaseOrder.getQuantity() <= returnAmount) {
                        leaseQueue.poll();
                        amoutToCalculate = leaseOrder.getQuantity();
                        returnAmount -= amoutToCalculate;
                    } else {
                        amoutToCalculate = returnAmount;
                        leaseOrder.setQuantity(leaseOrder.getQuantity() - returnAmount);
                        returnAmount = 0;
                    }
                    //Calculate money
                    calculateRent(leaseOrder, amoutToCalculate, o.getDate(), priceMap,false);
                }
            }
        }
        calculateLeftOverRent(orderQueueMap, endDate, priceMap);
        printTable();
        return true;
    }

    private boolean calculateRent(Order leaseOrder, int quantity, Date endDate, HashMap<Integer, Double[]> priceMap, boolean isLeftOver) throws SQLException {
        int days = Common.DaysBetween(leaseOrder.getDate(), endDate);
        double firstMonPrice;
        double unitPrice;
        if (!validatePriceMap(leaseOrder, priceMap)) {
            return false;
        }
        firstMonPrice = priceMap.get(leaseOrder.getProductId())[0];
        unitPrice = priceMap.get(leaseOrder.getProductId())[1];
        double rent = (firstMonPrice + Math.max(0, days - 30) * unitPrice) * quantity;
        Object[] row = new Object[]{leaseOrder.getProductId(), days, quantity, firstMonPrice, unitPrice, rent};
        if(isLeftOver){
            markLeftOverRecord(row);
        }
        totalRent += rent;
        tableRows.add(row);
        return true;
    }

    private boolean calculateLeftOverRent(HashMap<Integer, Queue<Order>> orderQueueMap, Date endDate, HashMap<Integer, Double[]> priceMap) throws SQLException {
        for (Map.Entry<Integer, Queue<Order>> entry : orderQueueMap.entrySet()) {
            if (!entry.getValue().isEmpty()) {
                Queue<Order> q = entry.getValue();
                while (!q.isEmpty()) {
                    Order o = q.poll();
                    if (!calculateRent(o, o.getQuantity(), endDate, priceMap, true)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void markLeftOverRecord(Object[] row){
        row[0] = "*"+row[0];
    }

    private boolean validatePriceMap(Order o, HashMap<Integer, Double[]> priceMap) throws SQLException {
        if (!priceMap.containsKey(o.getProductId())) {
            Product p = productDAO.getProduct(o.getProductId());
            if (p == null) {
                System.out.println("Product " + o.getProductId() + " not exist!!!");
                return false;
            }
            priceMap.put(o.getProductId(), new Double[]{p.getFirstMonthPrice(), p.getPrice()});
        }
        return true;
    }

    private void printTable() {
        String[] column = new String[]{"Product Id", "Rent Days", "Quantity", "First Month Price", "Unit Price", "Rent"};
        Object[][] data = new Object[tableRows.size() + 1][column.length];
        int i = 0;
        for (Object[] row : tableRows) {
            data[i] = row;
            i++;
        }
        data[i] = new Object[]{"", "", "", "", "Total Rent", totalRent};
        TextTable tt = new TextTable(column, data);
        Common.PrintCurrentDateTime();
        System.out.println("Rent = (FirstMonthPrice + Math.max(0, Days - 30) * UnitPrice) * Quantity");
        System.out.println("* before Product Id means products loss for this project");
        tt.printTable();
        System.out.println(Constant.SingleLine);
    }
}
