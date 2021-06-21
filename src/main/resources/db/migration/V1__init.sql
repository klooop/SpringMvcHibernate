CREATE TABLE credit_decision (
                                 contract_id bigserial primary key,
                                 credit_status varchar(100),
                                 time date,
                                 credit_sum int

);

CREATE TABLE credits (
                         id bigserial primary key,
                         surname varchar(100) not null ,
                         name varchar(100) not null ,
                         middle_name varchar(100),
                         passport_series int not null,
                         passport_number int not null,
                         is_married boolean,
                         address varchar(100) not null,
                         phone bigint not null,
                         work_period_months int,
                         work_position varchar(100),
                         work_organization varchar(100),
                         sum int not null,
                         status boolean,
                         answer_days int,
                         approved_sum int,
                         credit_decision_id bigint REFERENCES credit_decision (contract_id),
                         UNIQUE(credit_decision_id)
);








