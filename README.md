### UBC_BDD_JAVA

---

##### `1. What's the framework and why did you choose this ? ` 
I have implemented <b>Java-Cucumber Framework</b> using BDD Approach and used <b> Java-RestAssured library for API Calls</b>.
One of the main reason to implement this framework is because it's easy to read and validate for the tester's hence anyone can write the feature file because it's written in plain English format ( Using Given, When , then and And ).  

Tech Stacks 
1. Java-RestAssured Library : Simplifies a lot of complexity when making API calls as it contains inbuilt methods which makes life easy! 
2. JUnit 4  - for testing and asserting 
3. Java - 1.8 

--- 

##### `2. What's included in this project ? `
This project includes 1 `Scenario` ( present inside feature file `.feature`)  and 4 different step defs ( Test Cases ) 
1. Validate File Parameters
2. Validate Agent-Header
3. Post Call to Create new gist
4. Get Call for newly Created gist
  
---

##### `3. How do i run this code  ? `

Import as Maven Build Project. Once imported - do <b> clean project and update maven project </b> to make sure all the dependecies are downloaded properly

To run this code - have to import `GitHubGistTestRunner.launch` file.  
Steps for importing file 

1. Click on file 
2. Select Import 
3. Go to Run/Debug folder --> Double click launch configurations. 
4. Select folder where you had saved `GitHubGistTestRunner.launch` file and click on finish 
5. Click on `Run` --> `Run Configuration` and inside `JUnit` you should see the launch file. 
6. To run --> Click on Runner file and click Run button/ Test should start running. 


---


##### `4. How did you handle Authentication  ? `
I have used Bearer Token which will be used only for creating gist. 
Newly created gist will be public and will be created from my GitHub Repo - UserName : <b>Hgami</b>

---

##### `5. What's file name and Content   ? `
File name is UBC and Content is UBC IRP Student QA!
