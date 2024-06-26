package com.unla.grupo36.services.implementation;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.unla.grupo36.dtos.PersonDTO;
import com.unla.grupo36.entities.Person;
import com.unla.grupo36.repositories.IPersonRepository;
import com.unla.grupo36.services.IPersonService;


@Service("personService")
public class PersonService implements IPersonService {

	private IPersonRepository personRepository;

	private ModelMapper modelMapper = new ModelMapper();

	public PersonService(IPersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@Override
	public List<Person> getAll() {
		return personRepository.findAll();
	}

	@Override
	public Person insertOrUpdate(Person person) {
		return personRepository.save(person);
	}

	@Override
	public boolean remove(int id) {
		try {
			personRepository.deleteById(id);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public Optional<Person> findById(int id) throws Exception {
		return personRepository.findById(id);
	}

	@Override
	public Person findByName(String name) throws Exception {
		return personRepository.findByName(name).orElseThrow(
				() -> new Exception("ERROR no existe Persona con Nombre: " + name)
		);
	}

	@Override
	public List<PersonDTO> findByDegreeName(String degreeName) {
		return personRepository.findByDegreeName(degreeName)
				.stream()
				.map(person -> modelMapper.map(person, PersonDTO.class))
				.collect(Collectors.toList());
	}
}
