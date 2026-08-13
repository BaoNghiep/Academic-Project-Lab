using DAL.Entities;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DAL.Repositories
{
    public class EventRepo
    {
        private Sp25eventDbContext _db;
        public EventRepo()
        {
            _db = new Sp25eventDbContext();
        }

        // cau 3: viet ham lay het thong tin bang Event
        public List<Event> GetEvents()
        {
            return _db.Events.Include(x => x.Venue).ToList();
        }

        // ham search
        public List<Event> GetEvents(string keyword)
        {
            return _db.Events
                .Include(x => x.Venue)
                .Where(x => x.Description.ToLower().Contains(keyword.ToLower()) || x.Venue.Location.ToLower().Contains(keyword.ToLower())).ToList();
        }

        // ham create
        public void CreateEvent(Event ev)
        {
            ev.EventId = _db.Events.Max(x => x.EventId) + 1;
            _db.Events.Add(ev);
            _db.SaveChanges();
        }

        // ham delete
        public void DeleteEvent(Event ev)
        {
            _db.Events.Remove(ev);
            _db.SaveChanges();
        }

        // ham update
        public void UpdateEvent(Event ev)
        {
            var eventFromDb = _db.Events.Find(ev.EventId);

            eventFromDb.EventTitle = ev.EventTitle;
            eventFromDb.Description = ev.Description;
            eventFromDb.EventDate = ev.EventDate;
            eventFromDb.EventId = ev.EventId;
            eventFromDb.Status = ev.Status;
            eventFromDb.TicketPrice = ev.TicketPrice;

            eventFromDb.VenueId = ev.VenueId;

            _db.SaveChanges();
        }


    }
}
