# Sales Taxes 

### This is a project to print out the receipt details for a shopping basket. 


## Project Detailing & Use Cases

*	A receipt can contain any number of items.
*	Each item must contain an integer as quantity and decimal number as price.
*	Any imported item will have 5% tax.
* 	Any item that is not chocolate, book, or pill will have normal tax of 10%.
*	The rounding rules for sales tax are that for a tax rate of n%, a shelf price of p contains (np/100 rounded up to the nearest 0.05) amount of sales tax.
*	New line or exit with exit the project and print the receipt into the console. 

#### INPUT: 

##### Input 1: 
*	1 book at 12.49 
*	1 music CD at 14.99 
*	1 chocolate bar at 0.85 

##### Input 2: 
*	1 imported box of chocolates at 10.00 
*	1 imported bottle of perfume at 47.50 

#####  Input 3: 
*	1 imported bottle of perfume at 27.99 
*	1 bottle of perfume at 18.99 
*	1 packet of headache pills at 9.75 
*	1 box of imported chocolates at 11.25 

#### OUTPUT 

##### Output 1: 
*	1 book: 12.49 
*	1 music CD: 16.49 
*	1 chocolate bar: 0.85 
*	Sales Taxes: 1.50 Total: 29.83

##### Output 2: 
*	1 imported box of chocolates: 10.50 
*	1 imported bottle of perfume: 54.65 
*	Sales Taxes: 7.65 Total: 65.15

##### Output 3: 
*	1 imported bottle of perfume: 32.19 
*	1 bottle of perfume: 20.89 
*	1 packet of headache pills: 9.75 
*	1 imported box of chocolates: 11.85 
*	Sales Taxes: 6.70 Total: 74.68


 