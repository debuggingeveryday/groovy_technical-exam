import java.nio.file.Files
import java.text.SimpleDateFormat

class TextFileModifier {

   static def MAIN_DIRECTORY = "./"

   private static void timestamp(executedTime) {
      def ms = executedTime

      def seconds = (ms / 1000).toInteger()
      def minutes = (seconds / 60).toInteger()
      def hours = (minutes / 60).toInteger()

      String executedTimeLog = ""

      if (hours > 0) {
         executedTimeLog = "${hours} hours"
      } else if (minutes > 0) {
         executedTimeLog = "${minutes} minutes"
      } else if (seconds > 0) {
         executedTimeLog = "${seconds} seconds"
      } else {
         executedTimeLog = "${ms} miliseconds"
      }

      println("Executed Time: ${executedTimeLog}")
   }

   private static void backupFiles(directory, file) {
      File checkDirectoryBackup = new File(MAIN_DIRECTORY, "backup-files")

      if (!checkDirectoryBackup.isDirectory()) {
         checkDirectoryBackup.mkdir()
      }

      if (checkDirectoryBackup.isDirectory()) {
         File source = new File(directory, file)
         File destination = new File("${MAIN_DIRECTORY}/backup-files", file)
         destination << source.text

         println("Backup: ${source} to ${destination} successfully")
      }
   }

   private static boolean checkFile(directory, file) {
      File directoryPath = new File(directory)
      File filePath = new File("${directory}/${file}")

      if (directoryPath.isDirectory() && filePath.isFile()) {
         return true;
      }

      return false
   }

   private static boolean validation(fromDirectory, originalText, toDirectory, newText) {
      try {
        if (checkFile(fromDirectory, originalText)) {
            return true
        } else {
            return false
        }

      } catch (ArrayIndexOutOfBoundsException error) {
         println("Missing arguments: ", error)
      } catch (Exception error) {
         println("Error: ", error)
      }
   }

   private static void modifyFile(fromDirectory, originalText, toDirectory, newText) {
      // Backup file
      this.backupFiles(fromDirectory, originalText)

      File file = new File(fromDirectory, originalText)
      File newFile = new File(toDirectory, newText)

      if (file.renameTo(newFile)) {
         println "Moved: ${fromDirectory}${originalText} move to ${toDirectory}${newText} successfully"
         println "Modify: ${originalText} rename to ${newText} successfully"
      } else {
         println "Failed to renamed and move the file"
      }

   }

   static void main(String[] args) {
      /* 
         Arguments

         @fromDirectory = args[0] directory from
         @originalText = args[1] original file
         @toDirectory = args[2] directory to
         @newText = args[3]  new file
      */
      def (fromDirectory, originalText, toDirectory, newText) = args

      // Check if arguments is exist or not
      def isValid = this.validation(fromDirectory, originalText, toDirectory, newText)

      // If true will file modify and move
      if (isValid) {
         def startTime = System.currentTimeMillis()
   
         modifyFile(fromDirectory, originalText, toDirectory, newText)

         def endTime = System.currentTimeMillis()

         def executionTime = endTime - startTime
         this.timestamp(executionTime)
      }
   }
}