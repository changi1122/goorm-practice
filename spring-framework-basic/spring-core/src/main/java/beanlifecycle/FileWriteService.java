package beanlifecycle;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class FileWriteService {
    static java.io.FileWriter fileWriter;

    @PostConstruct
    private void openFile() throws IOException {
        System.out.println("파일을 엽니다.");
        fileWriter = new FileWriter(new File("output.txt"));
    }

    @PreDestroy
    private void closeFile() throws IOException {
        if (fileWriter != null) {
            System.out.println("파일을 닫습니다.");
            fileWriter.close();
        }
    }

    public void writeString(String string) throws IOException {
        fileWriter.write(string);
    }
}
