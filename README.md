# Stories

Stories is a BDD plugin for Grails that aims to make you life easier with functional testing.
Stories does't depend on any testing framework out there. It tries to use them all, offerind a way to organize your tests as Stories and Scenarios. 
What you do inside each scenario is your problem :)... you can use either Selenium, WebTest, HtmlUnit, etc.

## Using

To install just type:

    grails install-plugin http://github.com/downloads/xetorthio/stories/grails-stories-0.1.zip

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

