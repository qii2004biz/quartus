package ui;

import org.apache.tools.ant.types.resources.selectors.InstanceOf;

public class TargetPlatformMethods {

    public static String name(TargetPlatform type) {
        return type.description.toString ();
    }

    public static boolean isMobile(TargetPlatform type) {
        return type.equals ( TargetPlatform.ANY )
                || type.equals ( TargetPlatform.ANDROID_NATIVE )
                || type.equals ( TargetPlatform.IOS_NATIVE );
    }

    public static boolean isWeb(TargetPlatform type) {
        return type.equals ( TargetPlatform.ANY) || !isMobile ( type ) ;
    }

    public static TargetPlatform value(String descriptionText) {
        TargetPlatform val = TargetPlatform.valueOf ( descriptionText );
        return val != null ? val : TargetPlatform.ANY;
    }

    private TargetPlatformMethods() {
    }

    public enum TargetPlatform {
        CHROME ( "chrome" )
        , FIREFOX ( "firefox" )
        , EDGE ( "edge" )
        , IE ( "ie" )
        , SAFARI ( "safari" )
        , OPERA ( "opera" )
        , ANDROID_NATIVE ( "android" )
        , IOS_NATIVE ( "ios" )
        , ANY_WEB ( "any_web" )
        , ANY ( "any" );

        private String description;

        TargetPlatform(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return description;
        }
    }
}
