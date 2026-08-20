## UbytovnaTelegram

This program was created so that students of the Slovak Technical University (STU) could receive messages on Telegram related to finding accommodation on the website ubytovanie.stuba.sk.

Messages about receiving a dormitory accommodation come to you from your personal Telegram bot

### Setting up to launch applications:
This program works as follows.
For ease of use, a ready-made .jar file is available, which you can download from the release.

To start, you need to create your own Telegram bot. For this, use @BotFather

 1. Paste this .jar file into the desired directory.
 2. create a document with the given name and extension next to the .jar file 
 > config.properties
 3. The basis of this file looks like this:
 ```
 bot.token=
bot.name=
chat.id=

login=
password=
old=
 ```
- chat.token - just a token of you'r bot
- chat.name - name of a bot
- chat.id - hen using for the first time, leave it empty.
- login - you'r a login to ubytovanie
- password - you'r a password to ubytovanie
- old - you'r old

After this, run the program for the first time using the following command

```java -jar name_of_jar_file.jar```

Then, type the /chatid command into your Telegram bot.
After that, you'll receive your unique chat ID and paste it into config.properties.

After this, you can safely launch this program and wait for the much-desired message about receiving a place in the hostel.