# MatrixLeastCost
Finding least cost and path of cost matrix traversal. The traversal will be diagonal up, diagonal down and horizontal.
## Input
The input consists of a sequence of row specifications. Each row is represented by a series of
delimited integers on a single line.

## Output
The output consists of three values:

- Is a Valid input (the cost sum should be less then 50)
- Total least cost
- The path taken as a sequence of n delimited integers, each representing the rows
traversed in turn.

**Sample 1: (6X5 matrix normal flow)**

Input:

3 4 1 2 8 6

6 1 8 2 7 4

5 9 3 9 9 5

8 4 1 3 2 6

3 7 2 8 6 4
 
**Output:**

Valid : Yes

Cost : 16

Path : [1 2 3 4 4 5]
 
**Sample 2: (6X5 matrix normal flow)**

Input:

3 4 1 2 8 6

6 1 8 2 7 4

5 9 3 9 9 5

8 4 1 3 2 6

3 7 2 1 2 3
 
**Output:**

Valid : Yes

Cost : 11

Path : [1 2 1 5 4 5]
 
**Sample 3: (5X3 matrix with no path <50)**

Input:

19 10 19 10 19

21 23 20 19 12

20 12 20 11 10
 
**Output:**

Valid :No

Cost : 48

Path : [1 1 1]
 
**Sample 4: (1X5 matrix)**

Input:

5 8 5 3 5

**Output:**

Valid :Yes

Cost : 26

Path : [1 1 1 1 1]
 
**Sample 5: (5X1 matrix)**

Input:

5

8

5

3

5
 
**Output:**

Valid : Yes

Cost : 3

Path :[4]
