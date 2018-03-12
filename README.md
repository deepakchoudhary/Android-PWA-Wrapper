# Android-PWA-Wrapper-Kotlin

A sample Android Wrapper application to create a native Android App from an offline-capable Progressive Web App with KOTLIN SUPPORT.

Forked from https://github.com/xtools-at/Android-PWA-Wrapper where he has created the basic java thing ready.
I thought it would be useful and fun to have kotlin support as well

## Why would I use a wrapper?
I know, using a Wrapper-App to display a Website can feel a bit odd. But there are a few good reasons why you'd package your Web App like this.
- If you've got a very sophisticated UI already, it might make sense not to rebuild it from scratch for multiple platforms, especally if it's a Single Page Application already, that doesn't "feel" like a Website.
- There might be as well less competition for a given niche on App Stores, in comparison to Google directly.

## What it does
- Sets up a WebView just the way PWAs/SPAs like it (e.g. enables App cache and DOM storage, ...).
- Shows a loading spinner while fetching the Web App.
- Provided your Web App is Offline-capable, it only needs an Internet connection on the first startup. If this fails, it shows a native refresh widget.
- Opens all external URLs in the device's Browser instead.
- Checks for Internet connection and fetches Updates for your Web App accordingly.
- Is compatible down to JellyBean, although it's recommended to build for SDK Version >= 19 (KitKat). Building for SDK Version >= 21 (Lollipop) puts you on the safe side without having to worry too much about Browser support.
- APK-size < 1.4 MB. The latest cat video from WhatsApp weighs heavier ;)

## How to build your own
- Clone/fork repository
- Put your Web App's URL in _WEBAPP_URL_ in `Constants.java`
- Replace *app_name* in `strings.xml` with the name of your App
- Add your own primary colors to `colors.xml` (*colorPrimary, colorPrimaryDark, colorPrimaryLight*)
- Put your own icons in place:
    - Add your own _ic_launcher.png_ and _ic_launcher_round.png_ in the `mipmap` folders
    - Add your own _ic_appbar.png_ in the `drawables` folders. This is displayed in Android's _Recent Apps_ View on your app bar, so it should look nicely when placed on top of your primary color.
    - I recommend using [Android Asset Studio](https://romannurik.github.io/AndroidAssetStudio) to get the icons ready in no time
- Change the package name in `app/build.gradle`, *applicationId*
- Change `AndroidManifest.xml` -> `aplication` -> `activity` -> `intent-filter` to your own URLs/schemes/patterns/etc. or remove the `intent-filter` for `android.intent.action.VIEW` altogether
- Check `Constants.java` for more options
- Build App in Android Studio

### Only Pull Requests will be appreciated :)

## License
[GNU General Public License v3.0](https://www.gnu.org/licenses/gpl-3.0.en.html) - if you use it, we wanna see it!
