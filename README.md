# nfc-react-native-simple
Module for reading NFC id using React Native (android only)

Try code in example/index.android.js

Run app and touch your phone with any NFC card - it should display card ID.

To install this plugin you need:

Copy plugin using: git clone https://github.com/petersobolev/nfc-react-native-simple

Do following steps (AFAIK it is usual for native plugins, except for ones installed with rnpm):

Open up android/app/src/main/java/[...]/MainApplication.java

Add import ru.enlight.nfcreactnativesimple.NfcReactNativeSimplePackage; to the imports at the top of the file

Add new new NfcReactNativeSimplePackage() to the list returned by the getPackages() method

Append the following lines to android/settings.gradle:

include ':nfc-react-native-simple'
project(':nfc-react-native-simple').projectDir = new File(rootProject.projectDir, '../node_modules/nfc-react-native-simple/android')

Insert the following lines inside the dependencies block in android/app/build.gradle:

compile project(':nfc-react-native-simple')

For your project you need AndroidManifest.xml from example/ folder. Place it to the

android\app\src\main\AndroidManifest.xml

Other (small) AndroidManifest.xml is for plugin itself (you don't need to move it anythere)

Place nfc_tech_filter.xml from example/ folder to

android\app\src\main\res\xml\nfc_tech_filter.xml

Place index.android.js from example/ folder to the folder of your application (as usual)

Run   react-native run-android    to build it all (including java code)


Also check out another NFC react-native module: https://github.com/Lube/nfc-react-native
I spent a lot of time trying to get it work, but no way (may be now it does). 

License: MIT

