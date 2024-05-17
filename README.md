[![Maintainability](https://api.codeclimate.com/v1/badges/d653db82fe505495cbe9/maintainability)](https://codeclimate.com/github/ynb4gang/Json_Ticket_Parser/maintainability)

# Flight Ticket Analyzer

## Introduction

This project provides utilities for analyzing flight ticket data.
## Description
The project contains utility methods for analyzing flight ticket data. It includes functions for filtering tickets, calculating the minimum flight duration for each carrier, and determining the difference between the average and median prices for flights.

Usage
To use the utilities, you can follow these steps:

Ensure you have the necessary Java classes (Ticket, TicketReader, and Utils) in your project.
Create a JSON file containing flight ticket data in the format specified above.
Use the utility methods provided in Utils class to perform analysis on the ticket data.


## Example JSON file with ticket data (tickets.json)

```json
{
  "tickets": [
    {
      "origin": "LED",
      "origin_name": "Saint Petersburg",
      "destination": "SVO",
      "destination_name": "Moscow",
      "departure_date": "21.05.24",
      "departure_time": "08:00",
      "arrival_date": "21.05.24",
      "arrival_time": "09:30",
      "carrier": "SU",
      "stops": 0,
      "price": 5000
    },
    // Other tickets...
  ]
}
```

## Console Output
```output
Minimum flight time for each air carrier:
TK: 370 minutes
S7: 380 minutes
SU: 360 minutes
Average price: 13656.25
Median price: 13550.0
Difference between average price and median: 106.25
```
This output demonstrates the minimum flight time for each carrier and the difference between the average and median prices for flights.
