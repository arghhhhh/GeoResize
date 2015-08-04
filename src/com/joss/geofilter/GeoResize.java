package com.joss.geofilter;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;

public class GeoResize implements IXposedHookInitPackageResources {
	LinearLayout.LayoutParams batteryLayoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT);
	@Override
    public void handleInitPackageResources(InitPackageResourcesParam resparam) throws Throwable {
        if (!resparam.packageName.equals("com.snapchat.android"))
            return;
        resparam.res.hookLayout("com.snapchat.android", "layout", "battery_view", new XC_LayoutInflated() {
            @Override
            public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
            	View battery = (View) liparam.view.findViewById(
                        liparam.res.getIdentifier("battery_icon", "id", "com.snapchat.android"));
        				battery.setLayoutParams(batteryLayoutParams);
        				battery.setPadding(0, 0, 0, 0);
            }
        }); 
    }
}