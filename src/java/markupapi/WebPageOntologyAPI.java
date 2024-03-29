/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markupapi;

/**
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.oauth.OAuthRequestException;

import java.io.IOException;

import javax.inject.Named;

// [START header]
/**
 * An endpoint class we are exposing
 */
@Api(name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(ownerDomain = "helloworld.example.com",
                ownerName = "helloworld.example.com",
                packagePath = ""))
// [END header]

public class WebPageOntologyAPI {

    // [START hi]
    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "sayHi")
    public MarkUpApi sayHi(@Named("name") String name) {
        MarkUpApi response = new MarkUpApi();
        response.setData("Hi, " + name);

        return response;
    }
    //[END hi]

    // [START hi_user]
    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(
            name = "sayHiUser",
            httpMethod = ApiMethod.HttpMethod.GET)
    public MarkUpApi sayHiUser(@Named("name") String name, User user)
            throws OAuthRequestException, IOException {
        MarkUpApi response = new MarkUpApi();
        response.setData("Hi, " + name + "(" + user.getEmail() + ")");

        return response;
    }
    //[END hi_user]

    // [START post]
    @ApiMethod(
            name = "mybean.insert",
            path = "mybean",
            httpMethod = ApiMethod.HttpMethod.POST
    )
    public void insertFoo(MarkUpApi foo) {
    }
    // [END post]
    // [START resources]

    class Resp {

        private String foobar = "foobar";
        private String bin = "bin";

        @ApiResourceProperty
        private String visible = "nothidden";

        @ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
        public String getBin() {
            return bin;
        }

        public void setBin(String bin) {
            this.bin = bin;
        }

        @ApiResourceProperty(name = "baz")
        public String getFoobar() {
            return foobar;
        }

        public void setFoobar(String foobar) {
            this.foobar = foobar;
        }
    }

    public Resp getResp() {
        return new Resp();
    }
    // [END resources]

    @SuppressWarnings("unused")
    // [START lookmeup]
    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(
            name = "lookmeup",
            httpMethod = ApiMethod.HttpMethod.GET)
    public MarkUpApi lookMeUp(User user)
            throws OAuthRequestException, RequestTimeoutException, NotFoundException, IOException {
        MarkUpApi response = new MarkUpApi();

        // Look me up here...
        // response = lookup(user);
        //
        if (response != null) {
            // [START notfound]
            throw new NotFoundException(user.getEmail());
            // [END notfound]
        }
        if (true /* did we time out */) {
            // [START timeout]
            throw new RequestTimeoutException("lookMeUp() timed out");  // custom timeout exception
            // [END timeout]
        }

        return response;
    }
    // [END lookmeup]
}
