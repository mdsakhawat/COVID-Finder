# COVID-Finder
COVID Finder is an incredible android App for tracking  COVID19 patients around 100 meters from the current user .This App track the real time location of the users and shows the location on Map. The users who are COVID positive then their latitude and longitude are transfered to firebase real time database .Then all COVID positive users's locations are retrieved from firebase database and shown on map . The users  get a signal how many COVID positive users around 100 meters from the current user .
# Screenshots 
<img src="https://user-images.githubusercontent.com/69348740/97116045-dda87b80-1724-11eb-9d86-dcc276f036a0.png" width="200" height="300"/> <img src="https://user-images.githubusercontent.com/69348740/97145533-8c35d600-1790-11eb-983a-e15cfdafc495.png" width="200" height="300"/> <img src="https://user-images.githubusercontent.com/69348740/97146987-fea7b580-1792-11eb-887a-1c461b66ae5d.png" width="200" height="300"/> <img src="https://user-images.githubusercontent.com/69348740/100988849-5dbece00-357a-11eb-8840-e4e1935fb93f.jpg" width="200" height="300"/></br>
<img src="https://user-images.githubusercontent.com/69348740/97147065-1c751a80-1793-11eb-94b2-87097fa8f0ff.png" width="160" height="300"/> <img src="https://user-images.githubusercontent.com/69348740/97147000-049d9680-1793-11eb-98ad-38156d87c91c.png" width="160" height="300"/> <img src="https://user-images.githubusercontent.com/69348740/97147007-06675a00-1793-11eb-83f5-73b9fff111d3.png" width="160" height="300"/> <img src="https://user-images.githubusercontent.com/69348740/97147010-07988700-1793-11eb-861e-f928ac06c837.png" width="160" height="300"/> <img src="https://user-images.githubusercontent.com/69348740/97455700-534d5b00-1962-11eb-941d-2ca2c7f269f9.png" width="160" height="300"/></br>
# Features
### 0.Starting Page 
In this page user will see two options Start and Help . User can see all guideline for using this app by selecting Help and for starting user must be select the Start option . 
### 1. Sign Up : 
Sign up form for new users to create an account providing NAme , Email , Password and COVID19 status (Positive/Negative). A new user's record is created in Google Firebase Real Time Database. If an already existing user tries to sign up, he/she will not be able to do the same .
### 2. Verify Email : 
After the new user's information is successfully submitted a verification email will be sent to that user's email address and he/she must verify his/her email .
### 3. Login :
Login Page to allow only authorized users to login . Performs validation for email and password match . If an  user tries to login without verifying his/her email , the user will not be able to do that . 
### 4. Forgotten Password :
If an user forget his/her password , the user can set a new one from login page .
### 5. Home Page :
####     i. Location Permission : 
 If user's location is not enable , an alertdialog will be displayed for enable location .
####     ii. Google Map With Marker :
A google map is shown with markers and all markers idicate the COVID positive user's location except current user .The current user's location always is shown on map though the user is COVID positive or not .The current user's location is displayed on centre of the marked circle with marker and this marker has a title with current user's  city,locality and known name . The other markers indicate that these are the COVID positive user's current location and these marker have no Title . After every 20 seconds the location will be updated and only COVID positive users Latitude and Longitude  are pused in Firebase Real Time Database . Then all COVID positive users Latitude and Longitude are retrieved from Firebase Real Time Database . When retrieving Latitude and Longitude , checking and counting is it around 100 meters location or not from current user location . Finally, The current user will get a signal  how many COVID positive patients around 100 meters from he/she .
####     iii. Navigation Drawer :
   -  #####    Navigation Header :
        The current user's Name , Email and COVID status are shown here . 
   -  #####     COVID Hotline :
        Here the All COVID hotline numbers fo Bangladesh . If user tap any hotline number , the number will show on dialpad and he/she can easily call this number for   advice .
   -  #####    COVID Lab Test Info : 
        User can able to see and call all COVID Lab test hotline number  by selecting his/her own division easily for Bangladesh . 
   -  #####    COVID Hospital List :
        COVID hospitals name and contact number of specific hospital for Bangladesh are shown and  if user tap any hotline number , the number will display on dialpad and he/she can easily call this number 
   -  #####    BD COVID Update :
        By clicking this user can see how many new COVID positive patients , new deaths  in 24 hours and all other COVID information for Bangladesh.
   -  #####    Ambulance Info :
        User can see and call any hotline number for abulance by selecting his/her own division .
   -  #####    Change My COVID Status :
        User can able to change his/her COVID status from YES to NO or vice versa .
   -  #####     Sign Out :
        User can sign out by taping this if he/she want .If user is signed out , he/she must do the login when he/she again open the app otherwise the user will be moved directly to the Home page .          
   -  #####    Delete Account :
        User can able to delete his/her account .
   -  #####    Feedback :
        User can send feedback to the admin by sending a message .
# How the location tracking works ?
  <img src="https://user-images.githubusercontent.com/69348740/97615858-0478f180-1a46-11eb-8d67-f197bd98aea8.png" width="800" height="800"/>
     1. All user's device get continous location update from gps, network etc. then,</br>
     2. Write and update the latitude and longitude data to a specific firebase real time database for the device of user 1, 3 and user 5  then,</br>
     3. All device read that firebase real time database document and show all the location on map with markers and always show the current user's location with marker although the user is COVID negative .</br>
  
#  Installation
   
##  Permissions
   * Add in your Manifest file
```java
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="com.vogella.android.locationapi.maps.permission.MAPS_RECEIVE" />
    <uses-permission android:name="com.google.android.providers.gsf.permission.READ_GSERVICES" />
    <uses-permission android:name="android.permission.INTERNET" />
```
##  Add these in your first build.gradle file
   * In dependencies
```java
   classpath 'com.google.gms:google-services:4.2.0'
```
   * In repositories
```java
           maven()
                {
                    url 'https://maven.google.com'
                }
           maven()
                {
                    url 'https://jitpack.io'
                }
```
## Add these in your second build.gradle file
   * Add top of this file
   ```java
       apply plugin: 'com.google.gms.google-services'
   ```
   * In android
   ```java
     compileOptions {
        sourceCompatibility = 1.8
        targetCompatibility = 1.8
        }
   ```
   * In dependencies
   ```java
    implementation 'com.google.firebase:firebase-database:16.0.4'
    implementation 'com.google.firebase:firebase-auth:16.0.5'
    implementation 'androidx.legacy:legacy-support-v4:1.0.0'
    implementation 'com.google.android.material:material:1.0.0'
    implementation 'androidx.navigation:navigation-fragment:2.0.0'
    implementation 'androidx.navigation:navigation-ui:2.0.0'
    implementation 'androidx.lifecycle:lifecycle-extensions:2.0.0'
    implementation 'com.google.android.gms:play-services-maps:17.0.0'
    implementation 'com.github.karanchuri:PermissionManager:0.1.0'
    implementation 'com.google.android.gms:play-services-location:17.0.0'
   ```
## Add this in your Manifest file 
```java
          <meta-data
            android:name="com.google.android.geo.API_KEY"
            android:value="@string/google_maps_key" />
```
1. Add [GOOGLE MAP API KEY](https://developers.google.com/maps/documentation/android-sdk/get-api-key) in your project for google map and location ,</br>
2. [Register your project on firebase](https://firebase.google.com/docs/android/setup)  using RealTime Database and Firebase Authentication ,</br>
   *  Setup sign in method</br> 
<img src="https://user-images.githubusercontent.com/69348740/97700690-739e2680-1ad6-11eb-972a-20fbf9715d76.jpg" width="800" height="500"/></br>
   *  Setup data read-write rules for real time database</br>
<img src="https://user-images.githubusercontent.com/69348740/97700697-74cf5380-1ad6-11eb-9026-8036d73c407b.jpg" width="800" height="500"/></br>

## [Download this app ](https://drive.google.com/file/d/1cJ7o4N-E3ir4huE1l1QxALknbt2KtH5h/view?usp=sharing)


