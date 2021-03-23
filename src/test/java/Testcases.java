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
        assert (this.service.saveStudent("8", "Carls", 444) == 0);
    }

    @Test
    public void addStudentTest_fail() {
        assert (this.service.saveStudent("8", "Carls", 10098) == 1);
    }

    //name
    @Test
    public void addStudentTest_name_success() {
        assert (this.service.saveStudent("8", "Carls", 345) == 0);
    }

    @Test
    public void addStudentTest_name_fail() {
        assert (this.service.saveStudent("8", "", 345) == 1);
    }

    //id
    @Test
    public void addStudentTest_id_success() {
        assert (this.service.saveStudent("8", "Carls", 345) == 0);
    }

    @Test
    public void addStudentTest_id_fail() {
        assert (this.service.saveStudent("", "Carls", 345) == 1);
    }

    //group
    @Test
    public void addStudentTest_group_success1() {
        assert (this.service.saveStudent("8", "Carls", 112) == 0);
    }

    @Test
    public void addStudentTest_group_success2() {
        assert (this.service.saveStudent("8", "Carls", 111) == 0);
    }

    @Test
    public void addStudentTest_group_success3() {
        assert (this.service.saveStudent("8", "Carls", 936) == 0);
    }

    @Test
    public void addStudentTest_group_success4() {
        assert (this.service.saveStudent("8", "Carls", 937) == 0);
    }

    @Test
    public void addStudentTest_group_fail1() {
        assert (this.service.saveStudent("8", "Carls", 109) == 1);
    }

    @Test
    public void addStudentTest_group_fail2() {
        assert (this.service.saveStudent("8", "Carls", 110) == 1);
    }

    @Test
    public void addStudentTest_group_fail3() {
        assert (this.service.saveStudent("8", "Carls", 938) == 1);
    }

    @Test
    public void addStudentTest_group_fail4() {
        assert (this.service.saveStudent("8", "Carls", 939) == 1);
    }

    @Test
    public void addAssigment_id() {
        Tema tema = new Tema("id", "descriere", 4, 1);
        if (tema.getID() == null || tema.getID().equals("")) {
            assert (this.service.saveTema(tema.getID(), tema.getDescriere(), tema.getDeadline(), tema.getStartline()) == 1);
        } else {
            assert (this.service.saveTema(tema.getID(), tema.getDescriere(), tema.getDeadline(), tema.getStartline()) == 0);
        }
    }

    @Test
    public void addAssigment_description() {
        Tema tema = new Tema("id", "", 4, 1);
        if (tema.getDescriere() == null || tema.getDescriere().equals("")) {
            assert (this.service.saveTema(tema.getID(), tema.getDescriere(), tema.getDeadline(), tema.getStartline()) == 1);
        } else {
            assert (this.service.saveTema(tema.getID(), tema.getDescriere(), tema.getDeadline(), tema.getStartline()) == 0);
        }
    }

}
