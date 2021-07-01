package contracts

import org.springframework.cloud.contract.spec.Contract

import java.util.regex.Pattern

Contract.make {
    description "should register new user"
    request {
        url "/users"
        method POST()
        headers {
            header 'Content-Type': 'application/json'
        }
        body (
                email: value(anyEmail()),
                password: value(anyNonEmptyString()),
                userType: "MUSICIAN"
        )

    }
    response {
        status 201
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