/*batch import*/
SET GLOBAL local_infile=1;
load data local infile '/Users/ccffsun/IdeaProjects/ConsensusOne/src/data/productData' into table ConsensusOne.product fields terminated by ',';