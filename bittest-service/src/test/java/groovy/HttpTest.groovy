package groovy
import groovyx.net.http.HTTPBuilder
import static groovyx.net.http.Method.GET
import static groovyx.net.http.ContentType.TEXT

def http = new HTTPBuilder( 'http://www.google.com/search' )

http.request(GET,TEXT) { req ->
    uri.path = '/mail/help/tasks/'
    headers.'User-Agent' = 'Mozilla/5.0'

    //请求成功
    response.success = { resp, reader ->
        assert resp.statusLine.statusCode == 200
        println "My response handler got response: ${resp.statusLine}"
        println "Response length: ${resp.headers.'Content-Length'}"
        //System.out << reader // print response stream
    }

    //404
    response.'404' = { resp ->
        println 'Not found'
    }

    // 401
    http.handler.'401' = { resp ->
        println "Access denied"
    }

    //其他错误，不实现则采用缺省的：抛出异常。
    http.handler.failure = { resp ->
        println "Unexpected failure: ${resp.statusLine}"
    }
}