package stories.runners

import org.apache.http.client.HttpResponseException
import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import grails.util.Environment

/**
 * Created by IntelliJ IDEA.
 * User: mwaisgold
 * Date: 02/06/2010
 * Time: 17:33:14
 * 
 */

class StoryRestClientHelper {

    def client

    StoryRestClientHelper( ){
        def urlBase = makeServerURL()
        if (!urlBase.endsWith("/")){
            urlBase += "/" //Must end in slash for the rest client.
        }
        client = new groovyx.net.http.RESTClient( urlBase )
    }

    /**
     * Get the declared URL of the server from config, or guess at localhost for non-production
     */
    String makeServerURL() {
        def u = ConfigurationHolder.config.grails.serverURL
        if (!u) {
            // Leave it null if we're in production so we can throw
            if (Environment.current != Environment.PRODUCTION) {
                u = "http://localhost:${(System.getProperty('server.port') ?: "8080")}/${ApplicationHolder.getApplication().metadata['app.name']}/"
            }
        }
        return u
    }

    private validateParams(params){
        if (!params.resource){
            throw new IllegalArgumentException( "You must supply a 'resource' argument" )
        }
    }

    private def execute(params, validations, block){
        validateParams(params)
        def resp
        try{
            def resource = params.resource
            resp = block(resource)
        } catch( HttpResponseException e ){
            resp = e.response
        }
        validations.delegate = resp
        validations()
    }

    def get( params, validations ){
        execute(params, validations) {
            client.get([path: it])
        }
    }

    def post( params, data, validations ){
        execute(params, validations) {
            client.post([path: it], body: data)
        }

    }

    def delete( params, validations ){
        execute(params, validations) {
            client.delete([path: params.resource])
        }
    }

}
