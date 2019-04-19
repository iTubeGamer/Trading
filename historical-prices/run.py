import csv
import time
import sys
from decimal import *
from datetime import datetime

stopLossPct = Decimal(sys.argv[1])
csvFiles = sys.argv[2].split(',')

def stripQuotes(s):
    return Decimal(s.lstrip('\"').rstrip('\"').replace(".", "").replace(",", "."))

def stripDate(s):
    return s.lstrip('\"').rstrip('\"')

with open(f'results-{time.strftime("%Y%m%d-%H%M%S")}.csv', 'w') as csvfile:
    writer = csv.writer(csvfile, delimiter=';', quotechar='|')
    writer.writerow(['WKN', 'ROI', 'yearly ROI', 'profit', 'buyDate', 'buyPrice', 'sellDate', 'sellPrice'])
    combinedRoi = Decimal(0)
    fileCount = 0
    for m in csvFiles:
        data = []
        highestValue = Decimal(0)
        buyPrice = Decimal(0)
        sellPrice = Decimal(0)
        stopLoss = Decimal(0)
        sellDate = ""
        file = 'data/' + m + '.csv'

        with open(file, newline='') as csvfile:
            reader = csv.reader(csvfile, delimiter=';', quotechar='|')
            rows=[r for r in reader]

            for i in range(0, len(rows)):
                newRow = []
                newRow.append(stripDate(rows[i][0]))
                for j in range(1, 5):
                    newRow.append(stripQuotes(rows[i][j]))

                data.insert(0, newRow)

            buyPrice = data[0][4]
            buyDate = data[0][0]
            stopLoss = buyPrice * stopLossPct
            highestValue = buyPrice

            for i in range(1, len(data)):
                if(stopLoss >= data[i][3]):
                    sellPrice = stopLoss
                    sellDate = data[i][0]
                    break
                if(highestValue < data[i][2]):
                    highestValue = data[i][2]
                    stopLoss = highestValue * stopLossPct

            days = 0
            if(sellDate == ""):
                sellPrice = data[len(data) - 1][4]
                days = (datetime.strptime(data[len(data) - 1][0], '%d.%m.%Y') - datetime.strptime(buyDate, '%d.%m.%Y')).days
            else:
                days = (datetime.strptime(sellDate, '%d.%m.%Y') - datetime.strptime(buyDate, '%d.%m.%Y')).days

            roi = sellPrice / buyPrice - 1
            yearlyROI = roi * Decimal(365.25) / days
            combinedRoi += roi
            fileCount+=1
            profit = sellPrice - buyPrice
            print("WKN: %s, ROI: %.2f%%, Yearly ROI: %.2f%%, Profit: %.2f, bought: %s for %.2f, sold: %s for %.2f, holding period: %u days" %  (m,roi*100,yearlyROI*100,profit,buyDate,buyPrice,sellDate,sellPrice,days))
            writer.writerow([m, roi, yearlyROI, profit, buyDate, buyPrice, sellDate, sellPrice])
    print("average ROI: %.2f%%" % (100*combinedRoi/fileCount))
