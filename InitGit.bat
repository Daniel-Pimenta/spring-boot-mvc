cd C:\Dados\Dev\eclipse\workspace-sts\spring-boot-mvc
echo "# Spring Boot MCV" >> README.md

git init
git add .
git commit -m "first commit"
git remote add origin https://github.com/Daniel-Pimenta/spring-boot-mvc.git
git push -u origin master


git add .
git commit -m "Add Spring Security"
git push -u origin master

heroku create dotcom-springboot-mvc
git push heroku master

heroku addons:create heroku-postgresql:hobby-dev

git add .
git commit -m "Commit Heroku TST 2"
git push heroku master
heroku logs --tail






