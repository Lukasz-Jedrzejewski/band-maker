package contracts

import org.springframework.cloud.contract.spec.Contract

import java.util.regex.Pattern

Contract.make {
    description "should return user with given id"
    request {
        urlPath(value(consumer(regex('/users/[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}'))))
        method GET()
        headers {
            header 'Content-Type': 'application/json'
        }
    }
    response {
        status 200
        headers {
            header 'Content-Type': 'application/json'
        }
        body(
                id: value(anyUuid()),
                email: value(anyEmail()),
                password: value(anyNonEmptyString()),
                userType: value(anyNonEmptyString()),
                createDate: regex(Pattern.compile(/[\S\s]+/)),
                updateDate: regex(Pattern.compile(/[\S\s]+/)),
                removed: false
        )
    }
}