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

public class BigBangTests {

    Validator<Student> studentValidator;
    Validator<Tema> temaValidator;
    Validator<Nota> notaValidator;

    StudentXMLRepository fileRepository1;
    TemaXMLRepository fileRepository2;
    NotaXMLRepository fileRepository3;

    Service service;

    public BigBangTests() {
        this.studentValidator = new StudentValidator();
        this.temaValidator = new TemaValidator();
        this.notaValidator = new NotaValidator();
        this.fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        this.fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        this.fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        this.service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @Test
    public void addStudentTest() {
        assert (this.service.saveStudent("1", "Carls", 444) == 0);
    }

    @Test
    public void addAssignmentTest() {
        assert (this.service.saveTema("2", "lalala", 5, 1) == 0);
    }

    @Test
    public void addGradeTest() {
        assert (this.service.saveNota("1", "2", 7, 0, "cool") == 1);
    }

    @Test
    public void bigBangIntegration() {
        addStudentTest();
        addAssignmentTest();
        addGradeTest();
    }

}
