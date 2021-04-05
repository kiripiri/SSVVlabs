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

    //ADD STUDENT
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

    //ADD ASSIGNMENT
    //id
    @Test
    public void addAssigment_id() {
        Tema tema = new Tema("id", "descriere", 4, 1);
        if (tema.getID() == null || tema.getID().equals("")) {
            assert (this.service.saveTema(tema.getID(), tema.getDescriere(), tema.getDeadline(), tema.getStartline()) == 1);
        } else {
            assert (this.service.saveTema(tema.getID(), tema.getDescriere(), tema.getDeadline(), tema.getStartline()) == 0);
        }
    }



    //id
    @Test
    public void addAssigmentTest_id_valid() {
        assert (this.service.saveTema("8", "lalala", 5,1) == 0);
    }

    @Test
    public void addAssigmentTest_id_empty() {
        assert (this.service.saveTema("", "Carls", 6, 1) == 1);
    }
    @Test
    public void addAssigmentTest_id_null() {
        assert (this.service.saveTema(null, "Carls", 5, 2) == 1);
    }

    //descriere
    @Test
    public void addAssigment_description() {
        Tema tema = new Tema("id", "", 4, 10);
        if (tema.getDescriere() == null || tema.getDescriere().equals("")) {
            assert (this.service.saveTema(tema.getID(), tema.getDescriere(), tema.getDeadline(), tema.getStartline()) == 1);
        } else {
            assert (this.service.saveTema(tema.getID(), tema.getDescriere(), tema.getDeadline(), tema.getStartline()) == 0);
        }
    }

    @Test
    public void addAssigmentTest_description_valid() {
        assert (this.service.saveTema("8", "Carls", 6,2) == 0);
    }

    @Test
    public void addAssigmentTest_description_empty() {
        assert (this.service.saveTema("8", "", 5,3) == 1);
    }

    @Test
    public void addAssigmentTest_description_null() {
        assert (this.service.saveTema("8", null, 7,2) == 1);
    }

    //deadline
    @Test
    public void addAssigment_deadline() {
        Tema tema = new Tema("id", "lala", 2, 17);
        if (tema.getDeadline() < 1 || tema.getDeadline() > 14 || tema.getDeadline() < tema.getStartline()) {
            assert (this.service.saveTema(tema.getID(), tema.getDescriere(), tema.getDeadline(), tema.getStartline()) == 1);
        } else {
            assert (this.service.saveTema(tema.getID(), tema.getDescriere(), tema.getDeadline(), tema.getStartline()) == 0);
        }
    }
    @Test
    public void addAssigment_deadline_valid() {
        assert (this.service.saveTema("8", "Carls", 6,2) == 0);
    }

    @Test
    public void addAssigment_deadline_smaller() {
        assert (this.service.saveTema("8", "Carls", 0,2) == 1);
    }

    @Test
    public void addAssigment_deadline_bigger() {
        assert (this.service.saveTema("8", "Carls", 15,2) == 1);
    }

    //startline
    @Test
    public void addAssigment_startline() {
        Tema tema = new Tema("id", "lala", 1, 12);
        if (tema.getStartline() < 1 || tema.getStartline() > 14 || tema.getDeadline() < tema.getStartline()) {
            assert (this.service.saveTema(tema.getID(), tema.getDescriere(), tema.getDeadline(), tema.getStartline()) == 1);
        } else {
            assert (this.service.saveTema(tema.getID(), tema.getDescriere(), tema.getDeadline(), tema.getStartline()) == 0);
        }
    }

    @Test
    public void addAssigment_startline_valid() {
        assert (this.service.saveTema("8", "Carls", 6,2) == 0);
    }

    @Test
    public void addAssigment_startline_smaller() {
        assert (this.service.saveTema("8", "Carls", 5,0) == 1);
    }

    @Test
    public void addAssigment_startline_bigger() {
        assert (this.service.saveTema("8", "Carls", 6,15) == 1);
    }
}
