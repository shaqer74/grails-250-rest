package com.rest

import grails.rest.Resource

@Resource(uri = 'restTest', formats = 'json')
class RestTest {

    String name

    static constraints = {
    }
}
