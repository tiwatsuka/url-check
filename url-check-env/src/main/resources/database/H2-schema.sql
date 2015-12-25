CREATE TABLE users(
	username VARCHAR(128),
	password VARCHAR(60) NOT NULL,
	enabled BOOLEAN
);
CREATE TABLE authorities(
	username VARCHAR(128),
	authority VARCHAR(60) NOT NULL
);
COMMIT;