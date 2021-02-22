1.EXPOSED API URL

http://localhost:8082/investment/marketValue/investor/50001 //GET

http://localhost:8082/investment/marketValue/fund/20001 //GET

http://localhost:8082/investment/marketValue/20001/10001 //GET

http://localhost:8082/investment/mapping/investor_fund/50001/20004 POST

http://localhost:8082/investment/mapping/fund_holding/20004/10005 //POST	

http://localhost:8082/investment/holdings/getHoldings //GET

http://localhost:8082/investment/funds/getFunds //GET

http://localhost:8082/investment/investors/getAllInvestors //GET

http://localhost:8082/investment/fund/addFund //POST

http://localhost:8082/investment/holding/addHolding //POST

http://localhost:8082/investment/investor/addInvestor //POST

2.Added basic unit test and mvc test

3.Added code coverage with JACOCO

4.Added Logging using slf4j
