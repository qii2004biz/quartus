package frameworkTests;

import org.junit.Assert;
import org.junit.Test;
import ui.TargetPlatformMethods;
import ui.TargetPlatformMethods.TargetPlatform;

import static org.hamcrest.CoreMatchers.equalTo;
import static ui.TargetPlatformMethods.name;

public class TargetPlatformMethodsTests {
    private String browser="firefox";
    @Test
    public void validateBrowserIsFireFox () {
        System.setProperty ( "browser", "firefox" );
//        System.out.println ( TargetPlatform.FIREFOX);
        Assert.assertThat( System.getProperty ( "browser" )
                , equalTo(  name (TargetPlatform.FIREFOX) ));
    }
    @Test
    public void validateBrowserIsChrome () {
        System.setProperty ( "browser", "chrome" );
//        System.out.println ( TargetPlatform.CHROME);
        Assert.assertThat( System.getProperty ( "browser" )
                , equalTo(  name (TargetPlatform.CHROME) ));
    }
    @Test
    public void validateBrowserIsEdge () {
        System.setProperty ( "browser", "edge" );
//        System.out.println ( TargetPlatform.EDGE);
        Assert.assertThat( System.getProperty ( "browser" )
                , equalTo(  name (TargetPlatform.EDGE) ));
    }
    @Test
    public void validateBrowserIsIE () {
        System.setProperty ( "browser", "ie" );
//        System.out.println ( TargetPlatform.IE);
        Assert.assertThat( System.getProperty ( "browser" )
                , equalTo(  name (TargetPlatform.IE) ));
    }
    @Test
    public void validateBrowserIsAndroid () {
        System.setProperty ( "browser", "android" );
//        System.out.println ( TargetPlatform.ANDROID_NATIVE);
        Assert.assertThat( System.getProperty ( "browser" )
                , equalTo(  name (TargetPlatform.ANDROID_NATIVE) ));
    }
    @Test
    public void validateBrowserIsIos () {
        System.setProperty ( "browser", "ios" );
//        System.out.println ( TargetPlatform.IOS_NATIVE);
        Assert.assertThat( System.getProperty ( "browser" )
                , equalTo(  name (TargetPlatform.IOS_NATIVE) ));
    }
    @Test
    public void validateBrowserIsAnyWeb () {
        System.setProperty ( "browser", "any_web" );
//        System.out.println ( TargetPlatform.ANY_WEB);
        Assert.assertThat( System.getProperty ( "browser" )
                , equalTo(  name (TargetPlatform.ANY_WEB) ));
    }
    @Test
    public void validateBrowserIsAny () {
        System.setProperty ( "browser", "any" );
//        System.out.println ( TargetPlatform.ANY);
        Assert.assertThat( System.getProperty ( "browser" )
                , equalTo(  name (TargetPlatform.ANY) ));
    }
    @Test
    public void validateBrowserIsOPERA () {
        System.setProperty ( "browser", "opera" );
//        System.out.println ( TargetPlatform.OPERA);
        Assert.assertThat( System.getProperty ( "browser" )
                , equalTo(  name ( TargetPlatform.OPERA )) );
    }
    @Test
    public void validatePlatformTypeMobile () {
        System.setProperty ( "browser", "ios" );
//        System.out.println ( TargetPlatformMethods.value ( "IOS_NATIVE" ) );
        Assert.assertTrue ( TargetPlatformMethods.isMobile ( TargetPlatform.IOS_NATIVE) );
    }
    @Test
    public void validatePlatformTypeWeb () {
        System.setProperty ( "browser", "chrome" );
//        System.out.println ( TargetPlatformMethods.value ( "CHROME" ) );
        Assert.assertTrue (  TargetPlatformMethods.isWeb ( TargetPlatform.CHROME) );
    }
    @Test
    public void validatePlatformValue () {
        System.setProperty ( "browser", "chrome" );
//        System.out.println ( TargetPlatformMethods.value ( "CHROME" ) );
        Assert.assertThat (TargetPlatform.CHROME, equalTo ( TargetPlatformMethods.value ( "CHROME") )  );
    }
}
