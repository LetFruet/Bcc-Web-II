CREATE TABLE paciente (
    id_paciente int  NOT NULL,
    nm_paciente varchar(50)  NOT NULL,
    cpf_aluno varchar(14) NOT NULL UNIQUE,
    dt_nasc date NOT NULL,
    CONSTRAINT paciente_pk PRIMARY KEY (id_paciente)
);

CREATE TABLE medico (
    id_medico int  NOT NULL,
    nm_medico varchar(50)  NOT NULL,
    crm_medico varchar(6) NOT NULL UNIQUE,
    CONSTRAINT medico_pk PRIMARY KEY (id_medico)
);

CREATE TABLE especialidade (
    id_especialidade int  NOT NULL,
    nm_especialidade varchar(50)  NOT NULL UNIQUE,
    CONSTRAINT especialidade_pk PRIMARY KEY (id_especialidade)
);

-- foreign keys
ALTER TABLE medico ADD CONSTRAINT medico_especialidade
    FOREIGN KEY (id_especialidade)
    REFERENCES especialidade (id_especialidade)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

ALTER TABLE medico ADD CONSTRAINT medico_paciente
    FOREIGN KEY (id_paciente)
    REFERENCES paciente (id_paciente)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

