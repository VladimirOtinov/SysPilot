## Способы попроще
Учтите, что вам в любом случае будет необходимо установить/проверить наличие специальных программ для работоспособности *SysPilot*.
С этими программами вы можете ознакомиться в ```README.md``` файле, 
в подразделе ***Требования к системе и окружению***, их установить вы можете по инструкции в подразделе ***Установка необходимых компонентов***
---
Можно упростить запуск приложения, создав исполняемый файл (скрипт) или даже полноценный `.jar`-файл, который автоматически включает в себя JavaFX зависимости. Вот несколько подходов, как можно это сделать:

### 1. **Создать оболочку для запуска (Shell или Bash Script)**

Для Linux вы можете создать Bash-скрипт, чтобы запускать приложение с необходимыми параметрами.

1. Создайте файл `run.sh` в корневой папке проекта:

   ```bash
   touch run.sh
   ```

2. Вставьте в него следующую команду:

   ```bash
   #!/bin/bash
   java --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml -jar target/SysPilot-1.0-SNAPSHOT.jar
   ```

3. Дайте файлу права на выполнение:

   ```bash
   chmod +x run.sh
   ```

Теперь приложение можно запустить просто командой `./run.sh`.

### 2. **Создание исполняемого файла `.jar` с JavaFX-зависимостями**

Можно создать "жирный" `.jar` файл, который включает JavaFX и другие зависимости. Для этого обновим `pom.xml`:

1. Добавьте в `pom.xml` плагин `maven-shade-plugin`, чтобы собрать все зависимости в один `.jar`:

   ```xml
   <build>
       <plugins>
           <plugin>
               <groupId>org.apache.maven.plugins</groupId>
               <artifactId>maven-shade-plugin</artifactId>
               <version>3.2.4</version>
               <executions>
                   <execution>
                       <phase>package</phase>
                       <goals>
                           <goal>shade</goal>
                       </goals>
                       <configuration>
                           <transformers>
                               <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                                   <mainClass>com.vladimir.syspilot.SysPilotApplication</mainClass>
                               </transformer>
                           </transformers>
                       </configuration>
                   </execution>
               </executions>
           </plugin>
       </plugins>
   </build>
   ```

2. Скомпилируйте и создайте "жирный" `.jar` файл:

   ```bash
   mvn clean package
   ```

3. Запускайте приложение с новым `.jar`:

   ```bash
   java -jar target/SysPilot-1.0-SNAPSHOT-shaded.jar
   ```

### 3. **Создание `.desktop`-файла для Linux**

Вы также можете создать `.desktop`-файл для запуска приложения из меню приложений в Linux:

1. Создайте файл `SysPilot.desktop` в `~/.local/share/applications/`:

   ```ini
   [Desktop Entry]
   Name=SysPilot
   Exec=/path/to/run.sh
   Icon=/path/to/icon.png
   Terminal=false
   Type=Application
   Categories=Utility;
   ```

2. Установите права на выполнение:

   ```bash
   chmod +x ~/.local/share/applications/SysPilot.desktop
   ```

Теперь приложение должно отображаться в меню приложений.