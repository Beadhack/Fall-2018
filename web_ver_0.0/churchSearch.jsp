<!doctype html>
<html lang="en">
<!-- ***************************************************
 Patricia Renee Taylor 9/20/18
 Churches_R_Us
 Version 0.0
 
 Search page has fields to search and filter for church/house of worship by denomination, location
 pastors, etc. Uses text boxes, radio buttons, dropdown menus.
 
*************************************************** -->
<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <title>Find Your Church Home</title>
  <link href="https://fonts.googleapis.com/css?family=Frank+Ruhl+Libre|Open+Sans" rel="stylesheet">

<link rel="stylesheet" href="style.css">
</head>

<body>
  <header class="logo-box">
    <img class="logo" src="banknote.svg">
  </header>
  
  <main class="main-content">
    <div class="interface-box">
      <header class="page-heading">
        <h1>Find Your Spiritual Home</h1>
            <h2 style=margin-left:3rem;margin-top:1rem; >
              Select search terms and select enter button</h2>
      </header>
      <section class="content-body">
        <form class="login-form" name="loginForm">


          <fieldset>
            <label>City:</label>
            <input type="text" name="city"><br><br>
            
            <label>State/Province:</label>
            <select>
                <option value="GA">Georgia</option>
                  <option value="AL">Alabama</option>
                    <option value="FL">Florida</option>
                      <option value="TN">Tennessee</option>
                        <option value="SC">South Carolina</option>
            </select><br><br>    
            
            <label>Country:</label>
            <select>
                <option value="USA">USA</option>
                  <option value="CAN">Canada</option>
                    <option value="MEX">Mexico</option>
            </select><br> <br>
            
            <hr><br>
                                        
            <label>Zip/Postal code:</label>
            <input type="text" name="zipCode"><br><br>
            
            <label>Search Distance:</label>
             <select>
                <option value=5> 5 miles</option>
                  <option value=15> 15 miles</option>
                    <option value=50> 50 miles</option>
            </select><br><br>
            
            <label>Church Name:</label>
            <input type="text" name="churchName"><br><br>
            
            <label>Denomination:</label>
            <select>
                <option  >  Select an option</option>
                    <option value="AL">Baptist</option>
                        <option value="FL">Methodist</option>
                        <option value="TN">Catholic</option>
                            <option value="SC">Presbyterian</option>
                              <option value="GA">All Denominations</option>
            </select><br><br>    
            
            <label>Pastor Name:</label>
            <input type="text" name="pastorName"><br><br>
            
            <label>Congregation Size:</label>
            <select>
                <option value="<"<50">50 or less</option>
                  <option value="50-150">50 to 150</option>
                    <option value="150-500">150 to 500</option>
                      <option value="500-2000">500 to 2000</option>
                        <option value=">2000">over 2000</option>
            </select><br><br>  
          </fieldset>
          
          
            <button name="searchBtn"  href="#" class="button">Search</button>
            
         

        </form>
      </section>
    </div>
  </main>
  <footer>
    <p>2018 All Rights Reserved. &nbsp; &nbsp; &nbsp; &nbsp; Photo by John Westrock on Unsplash.</p>
</body>
</html>
