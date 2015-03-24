package daveapp



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class DaveController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Dave.list(params), model:[daveInstanceCount: Dave.count()]
    }

    def show(Dave daveInstance) {
        respond daveInstance
    }

    def create() {
        respond new Dave(params)
    }

    @Transactional
    def save(Dave daveInstance) {
        if (daveInstance == null) {
            notFound()
            return
        }

        if (daveInstance.hasErrors()) {
            respond daveInstance.errors, view:'create'
            return
        }

        daveInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'dave.label', default: 'Dave'), daveInstance.id])
                redirect daveInstance
            }
            '*' { respond daveInstance, [status: CREATED] }
        }
    }

    def edit(Dave daveInstance) {
        respond daveInstance
    }

    @Transactional
    def update(Dave daveInstance) {
        if (daveInstance == null) {
            notFound()
            return
        }

        if (daveInstance.hasErrors()) {
            respond daveInstance.errors, view:'edit'
            return
        }

        daveInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Dave.label', default: 'Dave'), daveInstance.id])
                redirect daveInstance
            }
            '*'{ respond daveInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Dave daveInstance) {

        if (daveInstance == null) {
            notFound()
            return
        }

        daveInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Dave.label', default: 'Dave'), daveInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'dave.label', default: 'Dave'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
