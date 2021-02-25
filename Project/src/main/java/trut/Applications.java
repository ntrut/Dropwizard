package trut;

import javax.servlet.http.HttpSession;

import org.eclipse.jetty.server.session.SessionHandler;
import org.jdbi.v3.core.Jdbi;

import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import trut.DB.AuthResource;
import trut.DB.leagueResource;
import trut.DB.playerResource;
import trut.DB.profileResource;
import trut.DB.teamResource;


public class Applications extends Application<Config>
{
	public static void main(String[] args) throws Exception{
		new Applications().run(args);
		
	}
	
	@Override
	public void initialize(Bootstrap<Config> bootstrap)
	{
		//do nothing yet
	}
	
	@Override
	public void run(Config config, Environment environment) throws Exception 
	{
		/*create a JDBI run here*/
		final profileResource po = new profileResource();
		final playerResource lo = new playerResource();
		final teamResource ho = new teamResource();
		final leagueResource as = new leagueResource();
		final AuthResource aa = new AuthResource();
		//final AuthResource dd = new AuthResource();
		
		final JdbiFactory factory = new JdbiFactory();
	    final Jdbi jdbi = factory.build(environment, config.getDataSourceFactory(), "com.mysql.jdbc.Driver");
	    environment.jersey().register(jdbi);
	    environment.jersey().register(po);
	    environment.jersey().register(lo);
	    environment.jersey().register(ho);
	    environment.jersey().register(as);
	    environment.jersey().register(aa);
	    environment.servlets().setSessionHandler(new SessionHandler());
	    environment.jersey().register(HttpSession.class);
	    
	    //environment.jersey().register(dd);
		
	    /*
	    
	    environment.jersey().register(new AuthDynamicFeature(
	            new BasicCredentialAuthFilter.Builder<User>()
	                .setAuthenticator(new BasicAuthenticator())
	                .setRealm("SECRET STUFF")
	                .buildAuthFilter()));
	    environment.jersey().register(RolesAllowedDynamicFeature.class);
	    //If you want to use @Auth to inject a custom Principal type into your resource
	    environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
	    */
	}

}
