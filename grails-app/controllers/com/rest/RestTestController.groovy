package com.rest



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RestTestController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond RestTest.list(params), [status: OK]
    }

    def get(RestTest restTestInstance) {
        if (restTestInstance == null) {
            render status: NOT_FOUND
            return
        }

        restTestInstance.validate()
        if (restTestInstance.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }

        respond restTestInstance.find(), [status: OK]
    }

    @Transactional
    def save(RestTest restTestInstance) {
        if (restTestInstance == null) {
            render status: NOT_FOUND
            return
        }

        restTestInstance.validate()
        if (restTestInstance.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }

        restTestInstance.save flush:true
        respond restTestInstance, [status: CREATED]
    }

    @Transactional
    def update(RestTest restTestInstance) {
        if (restTestInstance == null) {
            render status: NOT_FOUND
            return
        }

        restTestInstance.validate()
        if (restTestInstance.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }

        restTestInstance.save flush:true
        respond restTestInstance, [status: OK]
    }

    @Transactional
    def delete(RestTest restTestInstance) {

        if (restTestInstance == null) {
            render status: NOT_FOUND
            return
        }

        restTestInstance.delete flush:true
        render status: NO_CONTENT
    }
}
