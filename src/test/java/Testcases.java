import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

public class Testcases {

    Validator<Student> studentValidator;
    Validator<Tema> temaValidator;
    Validator<Nota> notaValidator;

    StudentXMLRepository fileRepository1;
    TemaXMLRepository fileRepository2;
    NotaXMLRepository fileRepository3;

    Service service;

    public Testcases() {
        this.studentValidator = new StudentValidator();
        this.temaValidator = new TemaValidator();
        this.notaValidator = new NotaValidator();
        this.fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        this.fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        this.fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        this.service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @Test
    public void addStudentTest_success() {
        assert(this.service.saveStudent("8","Carls",444)==0);
    }

    @Test
    public void addStudentTest_fail() {
        assert(this.service.saveStudent("8","Carls",10098)==1);
    }
}
