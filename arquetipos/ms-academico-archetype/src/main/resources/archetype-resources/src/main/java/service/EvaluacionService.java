#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${package}.dto.EvaluacionCreateRequest;
import ${package}.entity.Asignatura;
import ${package}.entity.Curso;
import ${package}.entity.Evaluacion;
import ${package}.repository.AsignaturaRepository;
import ${package}.repository.CursoRepository;
import ${package}.repository.EvaluacionRepository;

@Service
public class EvaluacionService {

	@Autowired
	private EvaluacionRepository repositorio;

	@Autowired
	private AsignaturaRepository asignaturaRepositorio;

	@Autowired
	private CursoRepository cursoRepositorio;

	public Evaluacion guardarEvaluacion(EvaluacionCreateRequest req) {
		Asignatura asignatura = asignaturaRepositorio.findById(req.asignaturaId())
				.orElseThrow(() -> new IllegalArgumentException("No existe la asignatura."));
		Curso curso = cursoRepositorio.findById(req.cursoId())
				.orElseThrow(() -> new IllegalArgumentException("No existe el curso."));
		Evaluacion ev = new Evaluacion();
		ev.setNombre(req.nombre());
		ev.setTipo(req.tipo());
		ev.setFecha(req.fecha());
		ev.setSemestre(req.semestre());
		ev.setAnio(req.anio());
		ev.setAsignatura(asignatura);
		ev.setCurso(curso);
		return repositorio.save(ev);
	}

	public List<Evaluacion> obtenerEvaluaciones() {
		return repositorio.findAll();
	}

	public List<Evaluacion> obtenerEvaluacionesPorCurso(Long cursoId) {
		return repositorio.findByCursoId(cursoId);
	}

	public Evaluacion obtenerEvaluacionID(Long id) {
		return repositorio.findById(id).orElse(null);
	}

	public Evaluacion modificarEvaluacion(Evaluacion ev_mod) {
		Evaluacion ev = repositorio.findById(ev_mod.getId()).orElse(null);
		if (ev == null) {
			return null;
		}
		ev.setNombre(ev_mod.getNombre());
		ev.setTipo(ev_mod.getTipo());
		ev.setFecha(ev_mod.getFecha());
		ev.setSemestre(ev_mod.getSemestre());
		ev.setAnio(ev_mod.getAnio());
		if (ev_mod.getAsignatura() != null && ev_mod.getAsignatura().getId() != null) {
			Asignatura a = asignaturaRepositorio.findById(ev_mod.getAsignatura().getId())
					.orElseThrow(() -> new IllegalArgumentException("No existe la asignatura."));
			ev.setAsignatura(a);
		}
		if (ev_mod.getCurso() != null && ev_mod.getCurso().getId() != null) {
			Curso c = cursoRepositorio.findById(ev_mod.getCurso().getId())
					.orElseThrow(() -> new IllegalArgumentException("No existe el curso."));
			ev.setCurso(c);
		}
		return repositorio.save(ev);
	}

	public String borrarEvaluacion(Long id) {
		repositorio.deleteById(id);
		return "Evaluacion eliminada correctamente.";
	}
}
