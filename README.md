# COVID-Finder
COVID Finder is a simple android App for tracking  COVID19 patients around 100 meters from the current user .This App track the real time location of the users and shows the location on Map. The users who are COVID positive then their latitude and longitude are transfered to firebase real time database .Then all COVID positive users's locations are retrieved from firebase database and showed on map  .The users can see how many COVID positive users around 100 meters from the current user with a Toast .The marker on map which has a title this marker indicates the current user and other markers which has no title indicates the other COVID positive users .

##  Screenshots 
<img src="https://user-images.githubusercontent.com/69348740/97116045-dda87b80-1724-11eb-9d86-dcc276f036a0.png" width="200" height="300"/> <img src="https://user-images.githubusercontent.com/69348740/97145533-8c35d600-1790-11eb-983a-e15cfdafc495.png" width="200" height="300"/> <img src="https://user-images.githubusercontent.com/69348740/97146987-fea7b580-1792-11eb-887a-1c461b66ae5d.png" width="200" height="300"/> <img src="https://user-images.githubusercontent.com/69348740/97146990-ffd8e280-1792-11eb-93be-8d87cf12dc33.png" width="200" height="300"/></br>
<img src="https://user-images.githubusercontent.com/69348740/97147065-1c751a80-1793-11eb-94b2-87097fa8f0ff.png" width="160" height="300"/> <img src="https://user-images.githubusercontent.com/69348740/97147000-049d9680-1793-11eb-98ad-38156d87c91c.png" width="160" height="300"/> <img src="https://user-images.githubusercontent.com/69348740/97147007-06675a00-1793-11eb-83f5-73b9fff111d3.png" width="160" height="300"/> <img src="https://user-images.githubusercontent.com/69348740/97147010-07988700-1793-11eb-861e-f928ac06c837.png" width="160" height="300"/> <img src="https://user-images.githubusercontent.com/69348740/97455700-534d5b00-1962-11eb-941d-2ca2c7f269f9.png" width="160" height="300"/></br>
## Features
### 1.Sign up : 
Sign up form for new user to create an account . A new user's information is created in Google Firebase . If an already existing user tries to sign up, he/she is prevented in doing the same . After the new user's information is submitted  a verification email is sent to that user's email .</br>
### 2.Verify Email : 
### 3.Login :
### 4.Forgotten Password :
### 5.Home Page : 
  - #### i.Google Map :</br>
  - #### ii.Navigation Drawer :</br>
    * #### Navigation Header :</br>
    * #### COVID Hotline :</br>
    * #### COVID Lab Test Info :</br>
    * #### COVID Hospital List :</br>
    * #### BD COVID Update :</br>
    * #### Ambulance Info :</br>
    * #### Change My COVID Status :</br>
    * #### Sign Out :</br>
    * #### Delete Account :</br>
    * #### Feedback :</br>
2.Login Page to allow only authorized users to login . Performs validation for username and password match . If an  user tries to login without verify his/her email , he/she is prevented in doing that . If an user forget his/her password , that user can set a new one . </br>

