package contracts

import org.springframework.cloud.contract.spec.Contract

import java.util.regex.Pattern

Contract.make {
    description "should save new institution data"
    request {
        urlPath(value(consumer(regex('/institution-data/[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}'))))
        method POST()
        headers {
            header 'Content-Type': 'application/json'
        }
        body(
                name: value(anyNonEmptyString()),
                city: value(anyNonEmptyString()),
                street: value(anyNonEmptyString()),
                streetNumber: value(anyNonEmptyString()),
                localNumber: value(anyNonEmptyString()),
                phoneNumber: value(anyNonEmptyString()),
                description: value(anyNonEmptyString())
        )
    }
    response {
        status 201
        headers {
            header 'Content-Type': 'application/json'
        }
        body([
                id: value(anyUuid()),
                name: value(anyNonEmptyString()),
                city: value(anyNonEmptyString()),
                street: value(anyNonEmptyString()),
                streetNumber: value(anyNonEmptyString()),
                localNumber: value(anyNonEmptyString()),
                phoneNumber: value(anyNonEmptyString()),
                description: value(anyNonEmptyString()),
                user: ([
                        id: value(anyUuid()),
                        email: value(anyEmail()),
                        password: value(anyNonEmptyString()),
                        userType: value(anyNonEmptyString()),
                        createDate: regex(Pattern.compile(/[\S\s]+/)),
                        updateDate: regex(Pattern.compile(/[\S\s]+/)),
                        removed: false
                ]),
                createDate: regex(Pattern.compile(/[\S\s]+/)),
                updateDate: null,
                removed: false
        ])
    }
}