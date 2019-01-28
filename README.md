[![Download](https://api.bintray.com/packages/cmykui/maven/cmykui/images/download.svg?version=0.3.8)](https://bintray.com/cmykui/maven/cmykui/0.3.8/link) [![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.svg?v=103)](https://github.com/ellerbrock/open-source-badges/) [![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://opensource.org/licenses/mit-license.php) [![Gitter chat](https://badges.gitter.im/gitterHQ/gitter.png)](https://gitter.im/cmykui/community)

![Logo](https://i.imgur.com/zVq1DF4.png)
 ### CMYK UI Framework
Small android framework made by group of students for uni project. Framework enables you to use several new components in your android project. Our motivation besides grade, was to make good looking components that would enable you to create super apps that have super UX and are easy to use but have a lot of non standard options. Every component can be upgraded and you can add a lot more functionality to whole project.

### Installation

Instaling CMYK UI is easy. All you need to do is add a few lines into your root build.gradle and modul bulid.gradle.<br>
1. Add maven repo to root bulid.gradle: 
```gradle
repositories {
        google()
        jcenter()
        maven {
            url  "https://cmykui.bintray.com/maven"
        }
    }  
  ```

2. Add two dependencies into module build.gradle:
```gradle
  dependencies {
    implementation 'com.android.support:percent:28.0.0'
    implementation 'hr.foi.air.cmykui:cmykui:0.3.8'
}
```

there you go! you are ready to use CMKY UI.

### Getting started, first usage

Lets try using CMYK UI button component. You can add component to your activity/fragment using xml:
```xml
<hr.foi.air.cmykui.component.ButtonComponent
        android:id="@+id/button"
        android:layout_width="match_parent"
        android:layout_height="50dp" />
```
now we have created CMYK UI button with id od *button*. Next step is to programmatically use that button. 
First we need to find that object from XML file, then we implement onClickListener method that will register if user has clicked on button. After user has clicked button show loading and after that we can trigger success or error message. 
```java
ButtonComponent button = findViewById(R.id.button);

button.setButtonText("My button");
button.setButtonSuccessText("Done!");
button.setButtonErrorText("Error!");

button.TextLoading = false;
button.isButtonLoader = true;

button.setOnClickListener(new OnClick<void>(){
  MyMethod();
});


// MyMethod
public void MyMethod() {
  // do something
  button.setActionSuccess();
}

```

as you can see before creating onClickListener we changed some properties like: 
- **setButtonText** => method that sets text for the button
- **setButtonSuccessText** => metohd that sets button success text
- **setButtonErrorText** => method that sets button error text
- **TextLoading** => if set to true shows loading text rather then loading animation
- **isButtonLoader** => if set to true after click button shows loading animation or text

after button is clicked we handled it and did some stuff and triggered **.setActionSuccess()** method that showed that action is done successfully.
Here is gif example of that button: 

![Buttoncomponent gif](https://media.giphy.com/media/lYibSocYE1ItcFs7gR/giphy.gif)

### Next steps
If you are interested in using CMYK UI you should take a look into our [wiki](https://github.com/wleorule/CMYK-UI/wiki) page where you can find specific details about all components and a lot more information. If you expirience some issues feel free to report them on [git issue tracker](https://github.com/wleorule/CMYK-UI/issues). You can always join our [gitter](https://gitter.im/cmykui/community) room and talk about CMYK UI project!

### For developers and contributors
First of all we want to thank you for the interest in developing CMKY UI. Since this is open source project feel free to fork, clone etc. and if you resolve some issue or create new component feel free to make new pull request we will gladly merge it and give you right credits for it! You can also join development room on [gitter](https://gitter.im/cmykui/Developers). 

### Made with ❤ by:
- Matija Cmrk ([github](https://github.com/matcmrk), matija.cmrk@gmail.com)
- Jerko Mitrić ([github](https://github.com/jera22), jerkomitric@gmail.com)
- Niko Pelicarić ([github](https://github.com/Niko135), nikokaptol@gmail.com])
- Leo Siniša Radošić ([github](https://github.com/wleorule), leo.radosic@gmail.com)

