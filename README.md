# historicalVar
the objective of the exercice is to calculate an indicator of profit or loss. 
This indicator represents the probable profit or loss (expressed in portfolio currency) for the portfolio based
on its past performances. The VaR (Value at Risk) is computed using the daily portfolio P&L (Profit and Loss) over a given
period (1 year, 2 years, …) and consist in taking the P&L at the n^th percentile.

# model : 
it contains the different object model to use in the application:
 - ValueRisk : it contains two attribute  : period and profitAndLoss number

# controller :
RegulatorService: the service RegulatorService allows to calculate the indicator of profit or loss

# exception :
HistoricalVarException: it's used to return error messages

# Unit test :
RegulatorTest : it contains some unit tests of the service RegulatorService.
