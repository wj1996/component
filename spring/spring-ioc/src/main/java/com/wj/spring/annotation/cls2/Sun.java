package com.wj.spring.annotation.cls2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sun {


    private Moon moon;

    @Autowired
    public Sun(Moon moon) {
        this.moon = moon;
        System.out.println("Sun()");
    }

    public Moon getMoon() {
        return moon;
    }

//    @Autowired
    public void setMoon(Moon moon) {
        this.moon = moon;
    }

    @Override
    public String toString() {
        return "Sun{" +
                "moon=" + moon +
                '}';
    }
}
