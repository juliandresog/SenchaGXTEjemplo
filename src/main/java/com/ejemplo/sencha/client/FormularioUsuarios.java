/**
* FormularioUsuarios
* Versión 1.0
* 25/03/2015
*
* Copyright(c) 2007-2015, Boos IT.
* admin@boos.com.co
*
* http://boos.com.co/license
**/

package com.ejemplo.sencha.client;

import com.ejemplo.sencha.client.entidades.Usuario;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.box.AutoProgressMessageBox;
import com.sencha.gxt.widget.core.client.box.MessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.FormPanelHelper;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.validator.MaxLengthValidator;
import com.sencha.gxt.widget.core.client.form.validator.RegExValidator;
import com.sencha.gxt.widget.core.client.tips.ToolTipConfig;
import java.util.List;

/**
 *
 * @author josorio
 */
public class FormularioUsuarios extends FramedPanel implements Editor<Usuario> {

    private TextField txtCorreo;
    private TextField txtClave;
    private TextField txtNombres;
    private TextField txtApellidos;
    private final FormPanel form;
    /**
     * ventana modal para el mensaje de espera
     */
    private AutoProgressMessageBox boxWait;
    //Para relacionar la entidad con el formulario.
    interface UsuariosDriver extends SimpleBeanEditorDriver<Usuario, FormularioUsuarios> {
    }
    private UsuariosDriver itemDriver = GWT.create(UsuariosDriver.class);
    /**
     * Entidad a trabajar
     */
    private Usuario usuario;

    /**
     * Constructor
     */
    public FormularioUsuarios() {
        setHeadingHtml("Ejemplo Sencha gxt, spring e hibernate");
        setButtonAlign(BoxLayoutContainer.BoxLayoutPack.CENTER);
        
        form = new FormPanel();

        VerticalLayoutContainer p = new VerticalLayoutContainer();
        form.add(p);
        form.setLabelWidth(200);

        VerticalLayoutContainer c = new VerticalLayoutContainer();
        setWidget(c);
        
        c.add(form);
        
        txtNombres = new TextField();
        txtNombres.setName("nombres");
        txtNombres.addValidator(new RegExValidator("^[^\\s].*", "No debe empezar por espacio en blanco o solo tener espacios en blanco"));
        txtNombres.setToolTipConfig(new ToolTipConfig("Nombres", "Escriba el nombre del usuario"));
        txtNombres.setAllowBlank(false);
        txtNombres.addValidator(new MaxLengthValidator(150));
        p.add(new FieldLabel(txtNombres, "Nombre *"), new VerticalLayoutContainer.VerticalLayoutData(0.8, -1));

        txtApellidos = new TextField();
        txtApellidos.setName("apellidos");
        txtApellidos.addValidator(new RegExValidator("^[^\\s].*", "No debe empezar por espacio en blanco o solo tener espacios en blanco"));
        txtApellidos.setToolTipConfig(new ToolTipConfig("Apellido", "Escriba el apellido del usuario"));
        txtApellidos.setAllowBlank(false);
        txtApellidos.addValidator(new MaxLengthValidator(150));
        p.add(new FieldLabel(txtApellidos, "Apellido *"), new VerticalLayoutContainer.VerticalLayoutData(0.8, -1));
        
        txtCorreo = new TextField();
        txtCorreo.setName("correo");
        txtCorreo.addValidator(new RegExValidator("^(\\w+)([-+.][\\w]+)*@(\\w[-\\w]*\\.){1,5}([A-Za-z]){2,4}$", "Email no válido"));
        txtCorreo.setToolTipConfig(new ToolTipConfig("Correo electrónico", "Escriba la dirección de correo electrónico del usuario"));
        txtCorreo.setEmptyText("ex. name@domain.com");
        txtCorreo.getFocusSupport().setPreviousId(getButtonBar().getId());
        txtCorreo.addValidator(new MaxLengthValidator(100));
        p.add(new FieldLabel(txtCorreo, "Correo electrónico"), new VerticalLayoutContainer.VerticalLayoutData(0.8, -1));
        
        txtClave = new TextField();
        txtClave.setName("clave");
        txtClave.addValidator(new RegExValidator("^[^\\s].*", "No debe empezar por espacio en blanco o solo tener espacios en blanco"));
        txtClave.setToolTipConfig(new ToolTipConfig("Documento", "Escriba la clave del usuario"));
        txtClave.setAllowBlank(false);
        txtClave.addValidator(new MaxLengthValidator(50));
        p.add(new FieldLabel(txtClave, "Clave *"), new VerticalLayoutContainer.VerticalLayoutData(0.8, -1));
        
        TextButton btnAceptar = new TextButton("Guardar");
        TextButton btnLimpiar = new TextButton("Cancelar");
        btnAceptar.addSelectHandler(listenerGuardar());
        btnLimpiar.addSelectHandler(listenerLimpiar());

        addButton(btnAceptar);
        addButton(btnLimpiar);
        
        itemDriver.initialize(this);
        
        // need to call after everything is constructed
        List<FieldLabel> labels = FormPanelHelper.getFieldLabels(this);
        for (FieldLabel lbl : labels) {
            //lbl.setLabelAlign(LabelAlign.TOP);
            lbl.setLabelWidth(150); 
        }
    }
    
    /**
     * Enlaza el formulario con el bean de un usuario
     *
     * @param entidad
     */
    protected void setBind(Usuario entidad) {
        form.reset();
        //deberia enlazar el formulario con la entidad ojo!.
        itemDriver.edit(entidad);
        //Saco la entidad del bean.
        usuario = entidad;
        //cargo formulario de forma manual con el bind se supone que esto no es necesario.
        entidadAFormulario(usuario);
    }
    
    /**
     * Lleno los datos del formulario con las datos de la entidad manualmente.
     *
     * @param usuario
     */
    private void entidadAFormulario(Usuario usuario) {
        txtCorreo.setValue(usuario.getCorreo()== null ? "" : usuario.getCorreo());
        txtNombres.setValue(usuario.getNombres());
        txtApellidos.setValue(usuario.getApellidos());        
        txtClave.setValue(usuario.getClave());
    }

    /**
     * Escucha el boton limpiar
     *
     * @return
     */
    private SelectEvent.SelectHandler listenerLimpiar() {
        return new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent ce) {
                form.reset();
                usuario = null;
            }
        };
    }

    /**
     * Escucha el boton guardar
     *
     * @return
     */
    private SelectEvent.SelectHandler listenerGuardar() {
        return new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                // Se chequea el formulario por errores
                // Si está bien
                if (form.isValid()) {
                    boxWait = new AutoProgressMessageBox("Progreso",
                            "Procesando los datos, por favor espere...");
                    boxWait.setProgressText("Procesando...");
                    boxWait.auto();
                    boxWait.show();

                    getService().guardarEntidad(formularioAEntidad(),
                            crearCallBackGuardarCambios());
                    
                } else // Si hay errores
                {
                    new AlertMessageBox("Alerta", "Debe llenar los campos obligatorios faltantes").show();                    
                }
            }
        };
    }
    
    /**
     * Servicio RPC
     * @return 
     */
    public static GWTServiceAsync getService() {
        // Create the client proxy. Note that although you are creating the
        // service interface proper, you cast the result to the asynchronous
        // version of the interface. The cast is always safe because the
        // generated proxy implements the asynchronous interface automatically.

        //return GWT.create(GWTService.class);
        
        final GWTServiceAsync svc = (GWTServiceAsync) GWT.create(GWTService.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/GWTService");
        
        return svc;
    }

    /**
     * Carga el objeto usuario a partir de los datos del formulario
     */
    private Usuario formularioAEntidad() {
        // Si no hay objeto usuario
        if (usuario == null) {
            usuario = new Usuario();
        }
        usuario.setCorreo(txtCorreo.getValue() == null ? "" : txtCorreo.getValue());
        usuario.setNombres(txtNombres.getValue());
        usuario.setApellidos(txtApellidos.getValue());
        usuario.setClave(txtClave.getValue());        

        return usuario;
    }

    /**
     * Crea el objeto de notificación de llegada de respuesta del servidor
     *
     * @return Objeto creado
     */
    private AsyncCallback<Integer> crearCallBackGuardarCambios() {

        return new AsyncCallback<Integer>() {
            @Override
            public void onFailure(Throwable arg0) {
                boxWait.hide();

                new AlertMessageBox("Alerta", "Se presentó un error de comunicación, "
                        + "por favor inténtelo de nuevo").show(); 
            }

            @Override
            public void onSuccess(Integer respuesta) {
                boxWait.hide();

                if (respuesta.equals(0)) { 
                    new AlertMessageBox("Alerta", "Guardado con exito").show();
                    form.reset();
                    usuario = null;
                } else {
                    new AlertMessageBox("Alerta", "Error guardando información en el servidor").show();

                }
            }
        };
    }
    
    
}
