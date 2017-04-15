/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.sasik.myapplication.backend;

import com.example.JokeJava;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.primudesigns.com",
                ownerName = "backend.primudesigns.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    @ApiMethod(name = "sayJoke")
    public MyBean sayJoke() {
        MyBean response = new MyBean();
        JokeJava jokeJava = new JokeJava();
        response.setData(jokeJava.getJoke());
        return response;
    }

}
