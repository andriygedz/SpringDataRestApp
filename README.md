# SpringDataRestApp

A barebones Java app, which can easily be deployed to Heroku.


[![Deploy to Heroku](https://www.herokucdn.com/deploy/button.png)](https://heroku.com/deploy)

## Running Locally

Make sure you have Java and Maven installed.  Also, install the [Heroku CLI](https://cli.heroku.com/).

```sh
$ git clone https://github.com/heroku/SpringDataRestApp.git
$ cd SpringDataRestApp
$ mvn install
$ heroku local:start
```

Your app should now be running on [localhost:5000](http://localhost:5000/).

To use a database, ensure you have a local `.env` file that reads something like this:

```
DATABASE_URL=postgres://localhost:5432/java_database_name
```

## Deploying to Heroku

```sh
$ heroku create
$ git push heroku master
$ heroku open
```
