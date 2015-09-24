<%@page import="com.fasterxml.jackson.core.JsonProcessingException"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.entity.Alternativa"%>
<%@page import="model.entity.Pergunta"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="java.util.List"%>
<%@page import="model.entity.Usuario"%>
<%@page import="controller.dao.UsuarioDAO"%>
<html>
<body>
    <h2>Jersey RESTful Web Application!</h2>
    <p><a href="webapi/myresource">Jersey resource</a>
    <p>Visit <a href="http://jersey.java.net">Project Jersey website</a>
    for more information on Jersey!
    <% /*
    <%! 
        String outPut() {
        ObjectMapper mapper = new ObjectMapper();
        Pergunta p = new Pergunta();
        List<Alternativa> a = new ArrayList<>();
        p.setAlternativaList(a);
        p.setEnunciado("tESTE");
        p.setAtivo(true);
        Alternativa al = new Alternativa();
        al.setDescricao("AAA");
        al.setCorreta(true);
        al.setPergunta(p);
        a.add(al);
        al = new Alternativa();
        al.setDescricao("BA");
        al.setCorreta(false);
        al.setPergunta(p);
        a.add(al);
        String josn = null;
        try {
            return mapper.writer().writeValueAsString(p);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    } */%>
    <%/*
    <%=outPut()*/%>
</body>
</html>
