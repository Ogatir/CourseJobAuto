import java.io.IOException;

public class Main {


    public static void main (String args[]){

        /**
         * Здесь обрабатываются параметры, введенные пользователем
         */
        DBG.LogWithoutTime("\nProgram execution begins\n------------------------------------------------------\n");
        ExecutionEngine executionEngine = new ExecutionEngine();
        if ((args.length>=3)&&(args[0].compareToIgnoreCase("create")==0)) { /*Создание файла курсовой*/
            String newDocumentName = args[1];
            String docUserEmails[]=new String[args.length-2];
            for (int i=2;i<args.length;i++){
                docUserEmails[i-2]=args[i];
            }
            try{
                executionEngine.Create(newDocumentName,docUserEmails);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if((args.length==2)&&(args[0].compareToIgnoreCase("get")==0)) {/*Скачивание файла*/
            String documentName=args[1];
            try{
                executionEngine.Download(documentName);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if ((args.length==1)&&(args[0].compareToIgnoreCase("viewDocuments")==0)){/*Просмотр списка файлов пользователя*/
            try {
                executionEngine.ViewDocuments();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if ((args.length==2)&&(args[0].compareToIgnoreCase("update")==0)){/*Обновление документа в храниилище*/
            try {
                executionEngine.Update(args[1]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        DBG.LogWithoutTime("\nProgram execution ends\n------------------------------------------------------\n");
       }
}

