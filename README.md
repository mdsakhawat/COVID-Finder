# COVID-Finder
COVID Finder is a simple android App for tracking  COVID19 patients around 100 meters from the current user .This App track the real time location of the users and shows the location on Map. The users who are COVID positive then their latitude and longitude are transfered to firebase real time database .Then all COVID positive users's locations are retrieved from firebase database and showed on map  .The users can see how many COVID positive users around 100 meters from the current user with a Toast .The marker on map which has a title this marker indicates the current user and other markers which has no title indicates the other COVID positive users .
# Screenshots 
<img src="https://user-images.githubusercontent.com/69348740/97116045-dda87b80-1724-11eb-9d86-dcc276f036a0.png" width="200" height="300"/> <img src="https://user-images.githubusercontent.com/69348740/97145533-8c35d600-1790-11eb-983a-e15cfdafc495.png" width="200" height="300"/> <img src="https://user-images.githubusercontent.com/69348740/97146987-fea7b580-1792-11eb-887a-1c461b66ae5d.png" width="200" height="300"/> <img src="https://user-images.githubusercontent.com/69348740/97146990-ffd8e280-1792-11eb-93be-8d87cf12dc33.png" width="200" height="300"/></br>
<img src="https://user-images.githubusercontent.com/69348740/97147065-1c751a80-1793-11eb-94b2-87097fa8f0ff.png" width="160" height="300"/> <img src="https://user-images.githubusercontent.com/69348740/97147000-049d9680-1793-11eb-98ad-38156d87c91c.png" width="160" height="300"/> <img src="https://user-images.githubusercontent.com/69348740/97147007-06675a00-1793-11eb-83f5-73b9fff111d3.png" width="160" height="300"/> <img src="https://user-images.githubusercontent.com/69348740/97147010-07988700-1793-11eb-861e-f928ac06c837.png" width="160" height="300"/> <img src="https://user-images.githubusercontent.com/69348740/97455700-534d5b00-1962-11eb-941d-2ca2c7f269f9.png" width="160" height="300"/></br>
# Features
### 1. Sign Up : 
Sign up form for new user to create an account . A new user's record is created in Google Firebase . If an already existing user tries to sign up, he/she will not be able to do the same .
### 2. Verify Email : 
After the new user's information is submitted a verification email will be sent to that user's email address and he/she must verify his/her email .
### 3. Login :
Login Page to allow only authorized users to login . Performs validation for email and password match . If an  user tries to login without verifying his/her email , the user will not be able to do that . 
### 4. Forgotten Password :
If an user forget his/her password , the user can set a new one from login page .
### 5. Home Page :
####     i. Location Permission : 
 If user's location is not enable , an alertdialog will be showed for enable location .
####     ii. Google Map With Marker :
A google map is showed with markers and all markers idicate the COVID positive user's location except current user .Te current user's location always is showed on map  if the user is COVID positive or not .The current user's location is showed on centre of the marked circle with marker and this marker has a title with current user's  city,locality and known name .The other markers indicate that these are the COVID positive user's current location and these marker have no title .The markers which are in the circle indicate that these locations are around 100 meters from the current user and these locations are the COVID positive user's location .After every 20 seconds the location will be updated .The current user will get a signal  how many COVID positive patients around 100 meters from he/she .
####     iii. Navigation Drawer :
   -  #####    Navigation Header :
        Here  The current user's Name , Email and COVID status are showed . 
   -  #####     COVID Hotline :
        All Bangladeshi COVID hotline number are showed if user can tap this .If user tap on a hotline number , the number showed on dialpad and he/she can easily call this     number .
   -  #####    COVID Lab Test Info :
        User can able to see the all Bangladeshi COVID Lab test hotline number by selecting his/her own division . If user tap any hotline number , the number showed on dialpad and                he/she can easily call this number .
   -  #####    COVID Hospital List :
        COVID hospitals name and contact number of specific hospital for Bangladesh are showed and  if user tap any hotline number , the number showed on dialpad and he/she can easily call this number 
   -  #####    BD COVID Update :
        By clicking this user can see how many new COVID positive patients , new dead  in 24 hours and all other COVID information for Bangladesh.
   -  #####    Ambulance Info :
        User can see all abulance contact number by selecting own division and  if user tap any hotline number , the number showed on dialpad and he/she can easily                       call this number 
   -  #####    Change My COVID Status :
        User can able to change his/her COVID status from YES to NO or vice versa .
   -  #####     Sign Out :
        User can sign out by taping this if he/she want .If user is signed out , he/she must do the login when he/she again open the app otherwise the user will be moved to the         Home page .          
   -  #####    Delete Account :
        User can able to delete his/her account .
   -  #####    Feedback :
        User can send feedback to admin by taping . Write a messege and tap the send button to send the feedback .
# How the location tracking works ?
  <img src="https://user-images.githubusercontent.com/69348740/97615858-0478f180-1a46-11eb-8d67-f197bd98aea8.png" width="800" height="800"/>
     1. All user's device get continous location update from gps, network etc. then,</br>
     2. Write and update the latitude and longitude data to a specific firebase real time database for the device of user 1, 3 and user 5 then,</br>
     3. All device read that firebase real time database document and show all the location on map with markers and always show the own user's location with marker although the 
     user is COVID negative .</br>
     
#  Installation
1. [Register your project on firebase](https://firebase.google.com/docs/android/setup), I am using RealTime Database and Firebase Authentication in this Application.</br>
   *  Setup sign in method</br> 
<img src="https://user-images.githubusercontent.com/69348740/97700690-739e2680-1ad6-11eb-972a-20fbf9715d76.jpg" width="800" height="600"/></br>
   *  Setup data read write rules for real time database</br>
<img src="https://user-images.githubusercontent.com/69348740/97700697-74cf5380-1ad6-11eb-9026-8036d73c407b.jpg" width="800" height="600"/></br>
2. Add [GOOGLE MAP API KEY](https://developers.google.com/maps/documentation/android-sdk/get-api-key) in your project  or
3.[Download this app ](https://drive.google.com/file/d/1IKGQS4DPNuBxgHYbT9S6KgbUVXQLzKbO/view?usp=sharing)
