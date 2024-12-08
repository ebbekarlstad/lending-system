## User Manual
Our Menu is fairly straight forward, the only slightly counterintuitive feature is where to delete, this is accessed through the "Edit" menus and will give you a chance of hitting 4 for delete once reached. In addition we use member IDs to access information about said member, in order to test this use ID "123456".

## Completion
We realize out implementation is not EXACT to the guidelines. Theoretically we have completed all requirements however our methods are slightly astray from the directions. We have information accessed through a member ID instead of showing all the members. We are also unsure of how STRICT our MVC is, we believe that everything follows MVC except for most of the error handling in the model, many of them throw errors with Strings instead of passing enums for the view to then print the corresponding set error message. 

## Difficulties
The project was smooth sailing until we started testing. The errors we encountered while testing were always linked to how we prevented dependencies. When we created a deep copy to prevent dependencies those copies would often get altered instead of the original, which threw us into quite the loop. This difficulty was ran into twice, once when doing automated testing of the transactions (setting items to available when the contract expires), this occured again when trying to delete an Item, the actual error for this was happening with a few methods for handling printing the catalog which made it difficult to debug. 


[design.md](design.md) contains the prescribed architectural design of the application.

## Building
The build must pass by running console command:  
`./gradlew build`  
Note that you should get a report over the quality like:
```
CodeQualityTests > codeQuality() STANDARD_OUT
    0 CheckStyle Issues in controller/App.java
    0 CheckStyle Issues in controller/Simple.java
    0 CheckStyle Issues in model/Simple.java
    0 CheckStyle Issues in view/Simple.java
    0 FindBugs Issues in controller/App.java
    0 FindBugs Issues in model/Simple.java
    0 FindBugs Issues in view/Simple.java
    0 FindBugs Issues in controller/Simple.java
```

## Running
The application should start by running console command:  
`./gradlew run -q --console=plain`
