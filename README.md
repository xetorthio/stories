# Stories

Stories is a BDD plugin for Grails that aims to make you life easier with functional testing.
Stories does't depend on any testing framework out there. It tries to use them all, offerind a way to organize your tests as Stories and Scenarios. 
What you do inside each scenario is your problem :)... you can use either Selenium, WebTest, HtmlUnit, etc.

## Using

To install just type:

    grails install-plugin http://github.com/downloads/xetorthio/stories/grails-stories-0.11.zip

To create a new story, just type:
    grails create-story SomeStory

This will generate a file in your test/stories folder. The name of the file in this case will be SomeStory.groovy.

You should edit that file and add you scenarios.

You can use the before and after closures to execute code before and after every scenario.

    story "This is a story", {
        before {
            //TODO: do something before every scenario
        }

        after {
            //TODO: do something after every scenario
        }

        scenario "This is one scenario", {
            assert 1 == 1
        }

        scenario "This is another scenario", {
            assert 1 == 0
        }
    }

When you run your tests all the stories will be executed as well.
In this case you will see something like:

    -------------------------------------------------------
    Running 2 stories tests...
    This is a story
        A scenario in the story - PASSED
        Another scenario in the story - FAILED..... Expression: (1 == 0)
    Tests Completed in 46ms ...
    -------------------------------------------------------
    Tests passed: 1
    Tests failed: 1
    -------------------------------------------------------

## Testing a REST API
This seems to be a hot topic right now. So lets do that with Storis!
As I said earlier, stories actually doesn't provide any specific language to write your tests. It's just a way to organize yourself (and in the future there will be some nice helpers for specific things :) ).
So in this example I will use groovy RestClient. In grails this is provided through a plugin called "rest".

    grails install-plugin rest

Now lets create a story and start testing!

    grails create-story FacebookTest

We edit test/stories/FacebookTestStory.groovy and put some code

    import groovyx.net.http.RESTClient

    story "As a consumer I want to get details of a user so I can show the user information in my own site", {
        scenario "Get user information anonymously without filtering", {
            def facebook = new RESTClient("https://graph.facebook.com/")
            def response = facebook.get(path:"jleibiusky")

            assert response.status == 200
            assert response.data.name == "Jonathan Leibiusky"
            assert response.data.first_name == "Jonathan"
        }
        
        scenario "Get user information anonymously filtering by fields", {
            def facebook = new RESTClient("https://graph.facebook.com/")
            def response = facebook.get(path:"jleibiusky", query:[fields:"name"])

            assert response.status == 200
            assert response.data.name == "Jonathan Leibiusky"
            assert response.data.first_name == null
        }
    }

And we are done!

License
-------

Copyright (c) 2010 Jonathan Leibiusky

Permission is hereby granted, free of charge, to any person
obtaining a copy of this software and associated documentation
files (the "Software"), to deal in the Software without
restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following
conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.

