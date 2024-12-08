# Stuff Lending Test Report
Document the results of your final system test below. You find instructions on the course homepage.

# Member
Create with a name, email and mobile phone number. A unique member id should be generated and assigned to the new member and the day of creation should be recorded.21/10 Pass
The member id should be 6 alpha-numeric characters.21/10 Pass
The email address and phone number needs to be unique (no other members can have the same email or phone number).21/10 Pass
Delete a member.21/10 Pass
Change a member's information. 21/10 Pass
Look at a specific members full information. 21/10 Pass
List all members in a simple way (Name, email, current credits, and number of owned items) 21/10 Pass
List all members in a verbose way (Name, email, information of all owned items (including who they are currently lent to and the time period)) 21/10 Pass

# Item
Create a new item for a member, the item should have a category (Tool, Vehicle, Game, Toy, Sport, Other), a name, a short description, the day of creation should be recorded, and a cost per day to lend the item.21/10 Pass
When created the owning member gets 100 credits.21/10 Pass
Delete an item 21/10 Pass
Change an item’s information. 21/10 Pass
View an items information including the contracts for an item (historical and future)21/10 Pass

# Contract
Establish a new lending contract with a starting day, an ending day and an item.21/10 Pass
Credits should be transferred according to the number of days and the price per day of the item 21/10 Pass
Can only be done if the lender has enough credits. 21/10 Pass
Can only be done if the item is available during the time period 21/10 Pass

# Time
Time is handled as a day counter, 0 is the first day and is set when the system starts. Time is not connected to the system in this proof of concept.21/10 Pass
Advance day. In order to properly test the system there needs to be a way to advance the current day without relying on the system time.
21/10 Pass
Prepare the design for persistence, i.e. add a persistence interface. Implement a hard coded "loading" of some members with items, i.e. 
21/10 
Passcreate some hard-coded data. You should not implement any persistent loading or saving to file or database etc. for the passing grade
21/10 Pass