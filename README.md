# ForestryService
The Forest Service is considering a proposal from a timber company to clearcut a nearby forest of Douglas Fir. Before this proposal may be approved, they must complete an environmental impact study. You have been asked to build an application that allows Rangers to track wildlife sightings in the area.
# Deployment
## Creating DB
Execute 

```
-- Database: wildlife_tracker_test

-- DROP DATABASE IF EXISTS wildlife_tracker_test;

CREATE DATABASE wildlife_tracker_test
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;
```  
  ## Creating the production DB
  -- Database: wildlife_tracker

-- DROP DATABASE IF EXISTS wildlife_tracker;
```
CREATE DATABASE wildlife_tracker
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;
## Creating tables

-- Table: public.Animals

-- DROP TABLE IF EXISTS public."Animals";

CREATE TABLE IF NOT EXISTS public."Animals"
(
    "ID" integer NOT NULL,
    "NAME" character varying(100) COLLATE pg_catalog."default" NOT NULL,
    "AGE" character varying(50) COLLATE pg_catalog."default",
    "HEALTH" character varying(50) COLLATE pg_catalog."default",
    CONSTRAINT "Animals_pkey" PRIMARY KEY ("ID")
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."Animals"
    OWNER to postgres;
	
	
	
	-- Table: public.SIGHTINGS

-- DROP TABLE IF EXISTS public."SIGHTINGS";

CREATE TABLE IF NOT EXISTS public."SIGHTINGS"
(
    "ID" integer NOT NULL,
    "ANIMAL_ID" integer,
    "ENGANGERED_ANIMAL_ID" integer,
    "LOCATION" character varying(50) COLLATE pg_catalog."default" NOT NULL,
    "RANGER_NAME" character varying(300) COLLATE pg_catalog."default" NOT NULL,
    "TIMESTAMP" timestamp without time zone, 
    CONSTRAINT "SIGHTINGS_pkey" PRIMARY KEY ("ID")
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."SIGHTINGS"
    OWNER to postgres;
```

