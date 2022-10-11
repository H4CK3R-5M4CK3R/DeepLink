package com.apphelper.DeepLink;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.androidmanifest.DataElement;
import com.google.appinventor.components.annotations.androidmanifest.CategoryElement;
import com.google.appinventor.components.annotations.androidmanifest.ActionElement;
import com.google.appinventor.components.annotations.androidmanifest.IntentFilterElement;
import com.google.appinventor.components.annotations.androidmanifest.ActivityElement;
import com.google.appinventor.components.annotations.UsesActivities;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;

@DesignerComponent(version = 2, description = "This is deeplink extension", category = ComponentCategory.EXTENSION, nonVisible = true, iconName = "zero-ico.jpg")
@UsesActivities(activities = {
        @ActivityElement(intentFilters = {
                @IntentFilterElement(actionElements = {
                        @ActionElement(name = "android.intent.action.VIEW")},
                        categoryElements = {
                        @CategoryElement(name = "android.intent.category.DEFAULT"),
                                @CategoryElement(name = "android.intent.category.BROWSABLE")},
                        dataElements = {
                            @DataElement(scheme = "prime",host="openintestapp")
                      })},name="com.sunny.PkgUtils.PkgUtils$DeepLinkActivity",exported = "true")})
@SimpleObject(external = true)
public final class DeepLink extends AndroidNonvisibleComponent
{
    public DeepLink(final ComponentContainer componentContainer) {
        super(componentContainer.$form());
    }
    
    public static class DeepLinkActivity extends Activity
    {
        protected void onCreate(final Bundle bundle) {
            super.onCreate(bundle);
            final Uri data = this.getIntent().getData();
            if (data != null && data.toString() != null) {
                final Intent launchIntentForPackage = this.getPackageManager().getLaunchIntentForPackage(this.getPackageName());
                launchIntentForPackage.putExtra("APP_INVENTOR_START", '\"' + data.toString() + '\"');
                this.startActivity(launchIntentForPackage);
                this.finish();
            }
        }
    }
}
